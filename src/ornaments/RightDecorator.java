package ornaments;

import UndoRedo.UndoHandler;
import UndoRedo.UndoableOrnament;
import shapes.Shapes;

import javax.swing.event.UndoableEditEvent;

public class RightDecorator extends ShapeDecorator
{
    UndoHandler undoHandler = UndoHandler.getInstance();

    public RightDecorator(Shapes decoratedShape)
    {
        super(decoratedShape);
    }

    @Override
    public void setOrnament(String position, String text)
    {
        decoratedShape.setOrnament(position, text);
        undoHandler.undoableEditHappened(new UndoableEditEvent(
                this, new UndoableOrnament(decoratedShape, position, text))
        );
    }
}
