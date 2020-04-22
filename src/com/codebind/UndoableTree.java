package com.codebind;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.undo.AbstractUndoableEdit;

public class UndoableTree extends AbstractUndoableEdit
{
    private DefaultTreeModel model;
    private DefaultMutableTreeNode treeNode;
    private DefaultMutableTreeNode root;

    public UndoableTree(DefaultTreeModel model,DefaultMutableTreeNode root, DefaultMutableTreeNode treeNode)
    {
        this.model = model;
        this.root = root;
        this.treeNode = treeNode;
    }

    public void undo(){
        super.undo();
        Tree.getInstance().removeTreeNode(treeNode);
    }

    public void redo(){
        super.redo();
        root.add((treeNode));
        model.reload(root);
    }
}

