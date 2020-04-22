package com.codebind;

import UndoRedo.UndoHandler;
import UndoRedo.UndoableTree;
import shapes.Shapes;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;

public class Tree extends JTree implements UndoableEditListener
{
    protected final DefaultMutableTreeNode root;        //the root of the tree
    protected final DefaultTreeModel model;
    protected final UndoHandler undoHandler;

    private static Tree instance = null;
    private DefaultMutableTreeNode selectedNode;
    private Shapes selectedShape;

    public Tree(){
        undoHandler = UndoHandler.getInstance();
        model =(DefaultTreeModel) this.getModel();
        model.setRoot(new DefaultMutableTreeNode("Shapes"));

        root = (DefaultMutableTreeNode) model.getRoot();
        this.setDragEnabled(true);
        this.setDropMode(DropMode.ON_OR_INSERT);
        this.setBackground(new Color(56,162,197));
        this.setPreferredSize(new Dimension(200,-1));

        //add listener to the tree for the selected node
        addTreeListener();
    }

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

                if (selectedNode.isLeaf()) {
                    selectedShape = (Shapes) nodeInfo;
                    selectedShape.setColor(Color.GRAY);
                }

                for (Shapes s : Draw.getInstance().getObjShapes())
                {
                    if(s != selectedShape)
                        s.setColor(Color.red);
                }
            }
        });
    }

    //add new node to the tree
    public void addTreeNode(DefaultMutableTreeNode obj)
    {
        root.add((obj));
        model.reload(root);
        undoHandler.undoableEditHappened(new UndoableEditEvent(
                this, new UndoableTree(model, root, obj)
        ));
    }

    //add new node to the tree
    public void addTreeNodeToNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child)
    {
        parent.add(child);
        model.reload(child);
    }

    public void removeTreeNode(DefaultMutableTreeNode obj){
        root.remove((obj));
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
