package com.codebind;

import shapes.Shapes;
import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;

public class UndoableDraw extends AbstractUndoableEdit
{
    private ArrayList<Shapes> listOfShapes;
    private Shapes shapes;

    public UndoableDraw(ArrayList<Shapes> listOfShapes, Shapes shapes){
        this.listOfShapes = listOfShapes;
        this.shapes =  shapes;
    }

    public void undo(){
        super.undo();
            listOfShapes.remove(shapes);
    }

    public void redo(){
        super.redo();
            listOfShapes.add(shapes);
    }
}
