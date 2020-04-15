package com.codebind;

public class Ellipse extends Shapes
{
    public Ellipse(String name, int height, int width, int posX, int posY)
    {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

        App.mainapp.addTreeNode(this);
    }
}
