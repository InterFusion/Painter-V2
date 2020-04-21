package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Shapes
{
    public Circle(String name, int posX, int posY, int width, int height)
    {
        super(name, posX, posY, height, height);
        shape = new Ellipse2D.Float(posX, posY, height, height);
    }

    public void refactor(int posX, int posY, int width, int height)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        Shape s = new Ellipse2D.Float(this.posX, this.posY, this.width, this.height);
        draw.setShapes(shape, s);
        tree.removeTreeNode(this);
        shape = s;
    }
}
