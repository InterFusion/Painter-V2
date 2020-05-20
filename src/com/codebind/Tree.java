package com.codebind;

import UndoRedo.UndoHandler;
import shapes.IShapes;
import shapes.Shapes;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.util.ArrayList;

public class Tree extends JTree implements UndoableEditListener
{
    private final DefaultMutableTreeNode root;        //the root of the tree
    private final DefaultTreeModel model;
    private final UndoHandler undoHandler;

    private static Tree instance = null;
    private DefaultMutableTreeNode selectedNode;
    private Shapes selectedShape;

    private Tree(){
        undoHandler = UndoHandler.getInstance();
        model =(DefaultTreeModel) this.getModel();
        model.setRoot(new DefaultMutableTreeNode("Shapes"));

        root = (DefaultMutableTreeNode) model.getRoot();

        this.setBackground(new Color(56,162,197));
        this.setPreferredSize(new Dimension(200,-1));

        //add listener to the tree for the selected node
        addTreeListener();
    }

    //to see which treenode is selected
    public void addTreeListener()
    {
        this.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                selectedNode = (DefaultMutableTreeNode)instance.getLastSelectedPathComponent();

                //Nothing is selected.
                if (selectedNode == null)
                    return;

                Object nodeInfo = selectedNode.getUserObject();

                //get the selectedshape and set color to gray
                selectedShape = (Shapes) nodeInfo;
                selectedShape.setColor(Color.GRAY);

                for (Shapes s : Draw.getInstance().getObjShapes())
                {
                    //set the color of every shape to red except the selectedshape
                    if(s != selectedShape)
                        s.setColor(Color.red);
                }
            }
        });
    }

    //update the tree everytime when a new shape is drawn
    public void updateTree(){
        root.removeAllChildren();
        for(Shapes s : Draw.getInstance().getObjShapes())
        {
            for (Shapes sub : s.getSubordinates())
            {
                //first add all the child to the parents
                if (!sub.getboolTree())
                {
                    s.getTreeNode().add(sub.getTreeNode());
                    sub.setboolTree(true);
                }
            }

            //add all the parents to the root
            if(!s.getboolTree())
            {
                root.add(s.getTreeNode());
                s.setboolTree(true);
            }
            s.setboolTree(false);
        }
        //reload the tree
        model.reload(root);
    }

    public Shapes getSelectedShape()
    {
        return selectedShape;
    }

    public DefaultMutableTreeNode getSelectedNode()
    {
        return selectedNode;
    }


    public static Tree getInstance(){
        if(instance == null){
            instance = new Tree();
        }
        return instance;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e)
    {
        undoHandler.addEdit(e.getEdit());
    }
}
