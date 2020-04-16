package com.codebind;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Tree extends JTree
{
    protected final DefaultMutableTreeNode root;
    protected final DefaultTreeModel model;

    private static Tree instance = null;
    private App mainApp;

    public Tree(){
        //create the root node
        root = new DefaultMutableTreeNode("Shapes");
        mainApp = App.getInstance();
        model =(DefaultTreeModel) mainApp.getTree().getModel();
        model.setRoot(root);

        mainApp.getTree().addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)mainApp.getTree().getLastSelectedPathComponent();

                if (selectedNode == null)
                    //Nothing is selected.
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

    public void addTreeNode(Shapes obj)
    {
        //add the child nodes to the root node
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
