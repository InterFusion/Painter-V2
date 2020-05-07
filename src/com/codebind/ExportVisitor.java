package com.codebind;

import shapes.*;

public class ExportVisitor implements Visitor
{
    private static ExportVisitor instance = null;               //the instance of this class

    public String export() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    @Override
    public String visitShapes(Shapes shapes)
    {
        return null;
    }

    @Override
    public String visitCircle(Circle circle)
    {
        return circle.getName() + " " + circle.getPosX() + " " + circle.getPosY() + " " + circle.getWidth() + " " + circle.getHeight();
    }

    @Override
    public String visitEllipse(Ellipse ellipse)
    {
        return ellipse.getName() + " " + ellipse.getPosX() + " " + ellipse.getPosY() + " " + ellipse.getWidth() + " " + ellipse.getHeight();
    }

    @Override
    public String visitRectangle(Rectangle rectangle)
    {
        return rectangle.getName() + " " + rectangle.getPosX() + " " + rectangle.getPosY() + " " + rectangle.getWidth() + " " + rectangle.getHeight();
    }

    @Override
    public String visitSquare(Square square)
    {
        return square.getName() + " " + square.getPosX() + " " + square.getPosY() + " " + square.getWidth() + " " + square.getHeight();
    }

    public static ExportVisitor getInstance(){
        if(instance == null){
            instance = new ExportVisitor();
        }
        return instance;
    }
}
