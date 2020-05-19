package ornaments;

import UndoRedo.UndoHandler;
import UndoRedo.UndoableOrnament;
import shapes.Shapes;

import javax.swing.event.UndoableEditEvent;

public class BottomDecorator extends ShapeDecorator
{
    UndoHandler undoHandler = UndoHandler.getInstance();

    public BottomDecorator(Shapes decoratedShape)
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
