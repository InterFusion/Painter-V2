package UndoRedo;

import com.codebind.Tree;
import shapes.Shapes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

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
        Tree.getInstance().updateTree();
    }

    public void redo(){
        super.redo();
        listOfShapes.add(shapes);
        Tree.getInstance().updateTree();
    }
}
