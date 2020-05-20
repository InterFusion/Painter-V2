package com.codebind;

import shapes.*;

import javax.swing.tree.TreeModel;
import java.io.FileWriter;
import java.io.IOException;

public class ExportVisitor implements Visitor
{
    private static ExportVisitor instance = null;               //the instance of this class

    public String export() {
        StringBuilder sb = new StringBuilder();
        StringBuilder whiteSpace = new StringBuilder();
        TreeModel model = Tree.getInstance().getModel();        //get the root of the tree with everthing in it
        if (model != null) {
            Object root = model.getRoot();
            walk(model, root, whiteSpace, sb);                  //walk trough the tree
        }
        else
            System.out.println("Tree is empty.");
        return sb.toString();
    }

    private void walk(TreeModel model, Object o, StringBuilder whiteSpace, StringBuilder sb)
    {
        int cc = model.getChildCount(o);
        sb.append(whiteSpace + "Group " + cc);              //add group with the childcount
        sb.append("\n");
        whiteSpace.append("\t");

        for( int i=0; i < cc; i++) {
            Object child = model.getChild(o, i );
            if (model.isLeaf(child)) {            //parent
                for(Shapes shape : Draw.getInstance().getObjShapes())
                {
                    if(shape.toString().contains(child.toString()))
                    {
                        sb.append(whiteSpace);
                        sb.append(visitShapes(shape));
                        sb.append("\n");
                    }

                }
            }
            else {                              //child
                for(Shapes shape : Draw.getInstance().getObjShapes())
                {
                    if(shape.toString().contains(child.toString()))
                    {
                        sb.append(whiteSpace);
                        sb.append(visitShapes(shape));
                        sb.append("\n");
                    }
                }
                walk(model, child, whiteSpace, sb);
            }
        }
    }

    @Override
    public String visitShapes(Shapes shapes)
    {
        return shapes.getName() + " " + shapes.getPosX() + " " + shapes.getPosY() + " " + shapes.getWidth() + " " + shapes.getHeight();
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
