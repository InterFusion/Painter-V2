package com.codebind;

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

    private Shapes selectedShape;

    public Tree(){
        undoHandler = UndoHandler.getInstance();
        model =(DefaultTreeModel) this.getModel();
        root = (DefaultMutableTreeNode) model.getRoot();
        model.setRoot(root);
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
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)instance.getLastSelectedPathComponent();

                //Nothing is selected.
                if (selectedNode == null)
                    return;

                Object nodeInfo = selectedNode.getUserObject();

                if (selectedNode.isLeaf()) {
                    selectedShape = (Shapes) nodeInfo;
                }
            }
        });
    }

    //add new node to the tree
    public void addTreeNode(Shapes obj)
    {
        root.add(new DefaultMutableTreeNode(obj));
        model.setRoot(root);
    }

    public void removeTreeNode(Shapes obj){
        model.
        model.setRoot(root);
    }

    public Shapes getSelectedShape()
    {
        return selectedShape;
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
