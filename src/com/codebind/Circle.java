package com.codebind;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle extends Shapes
{
    public Circle(String name, int posX, int posY, int height, int width)
    {
        super(name, posX, posY, height, width);
        shape = new Ellipse2D.Float(posX, posY, height, width);
    }

    public void Resize(int width, int height)
    {
        Shape s = new Ellipse2D.Float(this.posX, this.posY, width, height);
        draw.setShapes(shape, s);
        shape = s;
    }
}
