package UndoRedo;

import com.codebind.Draw;
import shapes.Shapes;
import javax.swing.undo.AbstractUndoableEdit;

public class UndoableOrnament extends AbstractUndoableEdit
{
    private final Shapes shapes;
    private final String position, text;

    public UndoableOrnament(Shapes shapes, String position, String text){
        this.shapes = shapes;
        this.position = position;
        this.text = text;
    }

    public void undo(){
        super.undo();
        shapes.deleteOrnament(position);
    }

    public void redo(){
        super.redo();
        shapes.setOrnament(position, text);
        Draw.getInstance().repaint();
    }
}
