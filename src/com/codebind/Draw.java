package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;



public class Draw extends JPanel
{
    private ArrayList<Shapes> objShapes = new ArrayList<Shapes>();      //list of objects
    private ArrayList<Shape> shapes = new ArrayList<Shape>();           //list to draw

    private Point startDrag, endDrag;
    private int shapeInt = 0;

    private static Draw instance = null;

    public Draw()
    {
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
            }

            public void mouseReleased(MouseEvent e)
            {
                if(shapeInt == 0)
                {
                    Shapes s = new Circle("Circle",  Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    objShapes.add(s);
                    shapes.add(s.shape);
                    System.out.println(s);
                }
                else if(shapeInt == 1)
                {
                    Shapes s = new Ellipse("Ellipse", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    objShapes.add(s);
                    shapes.add(s.shape);
                    System.out.println(s);
                }
                else if(shapeInt == 2)
                {
                    Shapes s = new Square("Square", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    objShapes.add(s);
                    shapes.add(s.shape);
                    System.out.println(s);
                }
                else if(shapeInt == 3)
                {
                    Shapes s = new Rectangle("Rectangle", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    objShapes.add(s);
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

    public void setShapes(Shape oldShape, Shape newShape)
    {
        shapes.remove(oldShape);
        shapes.add(newShape);
        repaint();
    }

    public static Draw getInstance(){
        if(instance == null){
            instance = new Draw();
        }

        return instance;
    }


}

