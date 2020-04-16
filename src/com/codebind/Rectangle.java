package com.codebind;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shapes
{
    public Rectangle(String name, int posX, int posY, int height, int width)
    {
        super(name, posX, posY, height, width);
        shape = new Rectangle2D.Float(posX, posY, height, width);
    }

    public void Resize(int width, int height)
    {
        Shape s = new Rectangle2D.Float(this.posX, this.posY, width, height);
        draw.setShapes(shape, s);
        shape = s;
    }
}
