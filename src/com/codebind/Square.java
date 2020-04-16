package com.codebind;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Square extends Shapes
{
    public Square(String name, int posX, int posY, int height, int width)
    {
        super(name, posX, posY, height, width);
        shape = new Rectangle2D.Float(posX, posY, height, width);
    }

    public void Resize(int width, int height)
    {
        Shape s = new Rectangle2D.Float(this.posX, this.posY, 100, 100);
        draw.setShapes(shape, s);
        shape = s;
    }
}
