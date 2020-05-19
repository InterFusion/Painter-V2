package UndoRedo;

import com.codebind.Draw;
import com.codebind.Tree;
import ornaments.IShapeDecorator;
import ornaments.ShapeDecorator;
import shapes.IShapes;
import shapes.Shapes;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;

public class UndoableOrnament extends AbstractUndoableEdit
{
    Shapes shapes;
    String position, text;

    public UndoableOrnament(Shapes shapes, String position, String text){
        this.shapes = shapes;
        this.position = position;
        this.text = text;
    }

    public void undo(){
        super.undo();
        shapes.deleteOrnament(position, text);
    }

    public void redo(){
        super.redo();
        shapes.setOrnament(position, text);
        Draw.getInstance().repaint();
    }
}
