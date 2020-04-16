package com.codebind;

public class Shapes
{
    public String name;
    public int posX, posY, width, height;

    public Shapes()
    {
        App.mainapp.addTreeNode(this);
    }

    public void Resize(int width, int height)
    {
        this.height = height;
        this.width = width;
    }
}
