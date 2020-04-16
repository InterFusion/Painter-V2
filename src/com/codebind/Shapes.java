package com.codebind;

import java.awt.*;

public class Shapes
{
    public String name;
    public int posX, posY, width, height;
    public Shape shape;
    public Draw draw;

    public Shapes(String name, int posX, int posY, int height, int width)
    {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
/*        this.draw = App.mainapp.getDraw();
        App.mainapp.addTreeNode(this);*/
    }

    public void Resize(int width, int height)
    {

    }

    public void move(int posX, int posY)
    {

    }
}
