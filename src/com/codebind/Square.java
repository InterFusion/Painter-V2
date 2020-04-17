package com.codebind;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Square extends Shapes
{
    public Square(String name, int posX, int posY, int width, int height)
    {
        super(name, posX, posY, height, height);
        shape = new Rectangle2D.Float(posX, posY, height, height);
    }

    public void refactor(int posX, int posY, int width, int height)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        Shape s = new Rectangle2D.Float(this.posX, this.posY, this.width, this.height);
        draw.setShapes(shape, s);
        shape = s;
    }
}
