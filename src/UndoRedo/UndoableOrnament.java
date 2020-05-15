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

    public UndoableOrnament(Shapes shapes){
        this.shapes = shapes;
        position = shapes.getPosition();
        text = shapes.getText();
    }

    public void undo(){
        super.undo();
        shapes.deleteOrnament();
    }

    public void redo(){
        super.redo();
        shapes.setOrnament(position, text);
        Draw.getInstance().repaint();
    }
}
