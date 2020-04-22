package shapes;



import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shapes
{
    public Rectangle(String name, int posX, int posY, int width, int height)
    {
        super(name, posX, posY, width, height);
        shape = new Rectangle2D.Float(posX, posY, width, height);
    }

    public void refactor(int posX, int posY, int width, int height)
    {
        Shape s = new Rectangle2D.Float(this.posX, this.posY, this.width, this.height);
        draw.setShapes(shape, s);
        shape = s;
    }
}
