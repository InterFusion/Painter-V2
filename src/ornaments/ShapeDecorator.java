package ornaments;
import UndoRedo.UndoHandler;
import UndoRedo.UndoableGroupDelete;
import UndoRedo.UndoableOrnament;
import com.codebind.MenuBar;
import shapes.Shapes;

import javax.swing.event.UndoableEditEvent;


public abstract class ShapeDecorator implements IShapeDecorator
{
    protected Shapes decoratedShape;

    public ShapeDecorator(Shapes decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void setOrnament(String position, String text)
    {
        decoratedShape.setOrnament(position, text);
    }

}
