package com.codebind;

import shapes.Shapes;

import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class UndoableDraw extends AbstractUndoableEdit
{
    protected ArrayList<Shape> shapes;
    protected Shapes shape;

    public UndoableDraw(ArrayList<Shape> shapes, Shapes shape){
        this.shapes = shapes;
        this.shape =  shape;
    }

    public void undo(){
        super.undo();
        shapes.remove(shape.shape);
    }

    public void redo(){
        super.redo();
        shapes.add(shape.shape);
    }
}
