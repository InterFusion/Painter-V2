package com.codebind;

import shapes.Shapes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.util.ArrayList;

public class UndoableRefactor extends AbstractUndoableEdit
{
    protected ArrayList<Shape> shapes;
    protected Shapes shape;


    public UndoableRefactor(ArrayList<Shape> shapes, Shapes shape){
        this.shapes = shapes;
        this.shape =  shape;
    }

    public void undo(){
        super.undo();
        shapes.remove(shape.shape);
        shapes.add(shape.oldShape);
    }

    public void redo(){
        super.redo();
        shapes.remove(shape.oldShape);
        shapes.add(shape.shape);

    }
}
