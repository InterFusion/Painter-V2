package com.codebind;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Square extends Shapes
{
    public Square(String name, int posX, int posY, int height, int width)
    {
        super(name, posX, posY, height, height);
        shape = new Rectangle2D.Float(posX, posY, height, height);
    }

    public void Resize(int width, int height)
    {
        Shape s = new Rectangle2D.Float(this.posX, this.posY, width, height);
        draw.setShapes(shape, s);
        shape = s;
    }
}
