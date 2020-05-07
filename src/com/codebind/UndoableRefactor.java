package com.codebind;

import shapes.Shapes;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.util.ArrayList;

public class UndoableRefactor extends AbstractUndoableEdit
{
    private Shapes shape;


    public UndoableRefactor(Shapes shapes){
        this.shape =  shapes;
    }

    public void undo(){
        super.undo();
        Shape oldShape = null;
        for(int i=0;i<shape.getOldShapes().size();i++)
        {
            if(shape.getShape() == shape.getOldShapes().get(i))
            {
                oldShape = shape.getOldShapes().get(i-1);
            }
        }
        shape.setShape(oldShape);
        Draw.getInstance().repaint();
    }

    public void redo(){
        super.redo();
        Shape oldShape = null;
        for(int i=0;i<shape.getOldShapes().size();i++)
        {
            if(shape.getShape() == shape.getOldShapes().get(i))
            {
                oldShape = shape.getOldShapes().get(i+1);
            }
        }
        shape.setShape(oldShape);
        Draw.getInstance().repaint();
    }
}
