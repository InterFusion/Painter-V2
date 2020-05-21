package com.codebind;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ellipse extends Shapes
{
    public Ellipse(String name, int posX, int posY, int width, int height)
    {
        super(name, posX, posY, width, height);
        shape = new Ellipse2D.Float(posX, posY, width, height);
    }

    //resize and move
    public void refactor(int posX, int posY, int width, int height)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        Shape s = new Ellipse2D.Float(this.posX, this.posY, this.width, this.height);               //creates a new shape with the new variables
        draw.setShapes(shape, s);
        shape = s;
    }
}
