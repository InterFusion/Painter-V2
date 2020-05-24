package shapes;

import UndoRedo.UndoableRefactor;
import javax.swing.event.UndoableEditEvent;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shapes
{
    public Ellipse(String name, int posX, int posY, int width, int height)
    {
        super(name, posX, posY, width, height);
        shape = new Ellipse2D.Float(posX, posY, width, height);
        setOldShapes(shape);
    }

    public void refactor(int posX, int posY, int width, int height)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        Shape s = new Ellipse2D.Float(this.posX, this.posY, this.width, this.height);           //create a new shape with the paramaters
        draw.repaint();
        shape = s;
        setOldShapes(shape);

        //add action to undoHandler
        undoHandler.undoableEditHappened(new UndoableEditEvent(
                this, new UndoableRefactor(this)
        ));
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitEllipse(this);
    }
}
