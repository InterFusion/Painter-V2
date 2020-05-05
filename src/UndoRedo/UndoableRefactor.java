package UndoRedo;

import com.codebind.Draw;
import com.codebind.Tree;
import shapes.Shapes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;
import java.util.ArrayList;

public class UndoableRefactor extends AbstractUndoableEdit
{
    private Shapes shapes;
    private Shape oldShape;
    private Shape firstOld;
    private Shape shape;


    public UndoableRefactor(Shapes shapes)
    {
        this.shapes = shapes;
        shape = shapes.getShape();
    }

    public void undo(){
        super.undo();
        oldShape = shapes.getOldShape();
        shapes.setOldShape(shape);
        shapes.setShape(oldShape);
        Draw.getInstance().repaint();
    }

    public void redo(){
        super.redo();
        shapes.setShape(shape);
        shapes.setOldShape(oldShape);
        Draw.getInstance().repaint();
    }
}
