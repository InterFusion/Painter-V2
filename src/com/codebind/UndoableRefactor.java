package com.codebind;

import shapes.Shapes;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.util.ArrayList;

public class UndoableRefactor extends AbstractUndoableEdit
{
    private ArrayList<Shapes> listOfShapes;
    private Shapes shapes;


    public UndoableRefactor(ArrayList<Shapes> listOfShapes, Shapes shapes){
        this.listOfShapes = listOfShapes;
        this.shapes =  shapes;
    }

    public void undo(){
        super.undo();
        Shape oldShape = shapes.getOldShape();
        Shape shapes1 = shapes.getShape();
        shapes.setShape(oldShape);
        shapes.setOldShape(shapes1);
        Draw.getInstance().repaint();
    }

    public void redo(){
        super.redo();
        Shape shapes1 = shapes.getOldShape();
        Shape oldShape = shapes.getShape();
        shapes.setShape(shapes1);
        shapes.setOldShape(oldShape);
        Draw.getInstance().repaint();
    }
}