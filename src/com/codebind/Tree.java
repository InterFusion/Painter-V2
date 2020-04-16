package com.codebind;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Tree extends JTree
{
    protected final DefaultMutableTreeNode root;
    private static Tree instance;

    public Tree(){
        //create the root node
        root = new DefaultMutableTreeNode("Shapes");
        //DefaultTreeModel model =(DefaultTreeModel) mainapp.tree1.getModel();
        //model.setRoot(root);
    }

    public void addTreeNode(Shapes obj)
    {
        //add the child nodes to the root node
        root.add(new DefaultMutableTreeNode(obj));

        //DefaultTreeModel model =(DefaultTreeModel) mainapp.tree1.getModel();
        //model.setRoot(root);
    }

    public void removeTreeNode(Shapes obj){

    }

    public static Tree getInstance(){
        if(instance == null){
            instance = new Tree();
        }
        return instance;
    }

}
