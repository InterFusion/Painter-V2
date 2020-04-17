package com.codebind;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class Tree extends JTree
{
    protected final DefaultMutableTreeNode root;        //the root of the tree
    protected final DefaultTreeModel model;

    private static Tree instance = null;

    public Tree(){
        root = new DefaultMutableTreeNode("Shapes");
        model =(DefaultTreeModel) this.getModel();
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
                    Shapes s = (Shapes) nodeInfo;
                    System.out.println(s.name);
                    System.out.println(s.posX);
                    System.out.println(s.posY);
                    System.out.println(s.width);
                    System.out.println(s.height);
                    s.Resize(100,100);
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

    public static Tree getInstance(){
        if(instance == null){
            instance = new Tree();
        }
        return instance;
    }

}
