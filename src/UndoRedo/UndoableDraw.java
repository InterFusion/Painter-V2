package UndoRedo;

import shapes.Shapes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class UndoableDraw extends AbstractUndoableEdit
{
    protected ArrayList<Shapes> listOfShapes;
    protected Shapes shapes;


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
