package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;



public class Draw extends JPanel
{
    private ArrayList<Shapes> objShapes = new ArrayList<Shapes>();          //list of objects
    private ArrayList<Shape> shapes = new ArrayList<Shape>();           //list to draw

    private Point startDrag, endDrag;                               //start and endpoint
    private int shapeInt = 0;                                       //select which shape to draw

    private static Draw instance = null;

    public Draw()
    {
        /*setSize(1000,700);
        setLocation(106, 0);*/
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
            }

            //checks to see which shape to draw
            public void mouseReleased(MouseEvent e)
            {
                Shapes s = null;
                if(shapeInt >= 0)
                {
                    if(shapeInt == 0)
                        s = new Circle("Circle",  Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    else if(shapeInt == 1)
                        s = new Ellipse("Ellipse", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    else if(shapeInt == 2)
                        s = new Square("Square", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    else if(shapeInt == 3)
                        s = new Rectangle("Rectangle", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));

                    objShapes.add(s);
                    assert s != null;
                    shapes.add(s.shape);
                    System.out.println(s);
                }
                repaint();

                startDrag = null;
                endDrag = null;
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            { endDrag = new Point(e.getX(), e.getY());
            }
        });
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    //will draw whats necessary to Graphics object
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for (Shape s : shapes)
        {
            g2.draw(s);
            g2.setPaint(Color.RED);
            g2.fill(s);
        }
    }

    public void setShape(int x)
    {
        shapeInt = x;
    }

    //replace the old shape for the new shape || resize
    public void setShapes(Shape oldShape, Shape newShape)
    {
        shapes.remove(oldShape);
        shapes.add(newShape);
        repaint();
    }

    public ArrayList<Shapes> getObjShapes() {
        return objShapes;
    }

    public void makeShape(String name, int posX, int posY, int width, int height)
    {
        Shapes s = null;
        switch (name)
        {
            case "Circle":
                s = new Circle("Circle",  posX, posY, width, height);
                break;
            case "Ellipse":
                s = new Ellipse("Ellipse",  posX, posY, width, height);
                break;
            case "Square":
                s = new Square("Square",  posX, posY, width, height);
                break;
            case "Rectangle":
                s = new Rectangle("Rectangle",  posX, posY, width, height);
                break;
        }

        if(s == null)
            return;

        objShapes.add(s);
        shapes.add(s.shape);
        repaint();
    }

    public static Draw getInstance(){
        if(instance == null){
            instance = new Draw();
        }
        return instance;
    }


}

