package com.codebind;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Tree extends JTree
{
    protected final DefaultMutableTreeNode root;        //the root of the tree
    protected final DefaultTreeModel model;

    private static Tree instance = null;
    private App mainApp;

    private Shapes selectedShape;

    public Tree(){
        root = new DefaultMutableTreeNode("Shapes");
        mainApp = App.getInstance();
        model =(DefaultTreeModel) mainApp.getTree().getModel();
        model.setRoot(root);

        //add listener to the tree for the selected node
        addTreeListener();
    }

    public void addTreeListener()
    {
        mainApp.getTree().addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)mainApp.getTree().getLastSelectedPathComponent();

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

}
