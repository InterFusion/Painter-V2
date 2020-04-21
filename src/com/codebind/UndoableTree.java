package com.codebind;

import shapes.Shapes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.undo.AbstractUndoableEdit;
import java.net.HttpRetryException;

public class UndoableTree extends AbstractUndoableEdit
{
    protected DefaultTreeModel model;
    protected Shapes shape;
    protected DefaultMutableTreeNode root;

    public UndoableTree(DefaultTreeModel model,DefaultMutableTreeNode root, Shapes shape)
    {
        this.model = model;
        this.root = root;
        this.shape = shape;
    }

    public void undo(){
        super.undo();

    }

    public void redo(){
        super.redo();
        int i = root.getChildCount();
        model.insertNodeInto((MutableTreeNode) shape, root, i);
    }
}
