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

            //veranderen in een switch of iets anders??
            public void mouseReleased(MouseEvent e)
            {
                Shapes s;
                switch (shapeInt){
                    case 0:
                        s = new Circle("Circle",  Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                        objShapes.add(s);
                        shapes.add(s.shape);
                        System.out.println(s);
                        break;
                    case 1:
                        s = new Ellipse("Ellipse", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                        objShapes.add(s);
                        shapes.add(s.shape);
                        System.out.println(s);
                        break;
                    case 2:
                        s = new Square("Square", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                        objShapes.add(s);
                        shapes.add(s.shape);
                        System.out.println(s);
                        break;
                    case 3:
                    s = new Rectangle("Rectangle", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
                    objShapes.add(s);
                    shapes.add(s.shape);
                    System.out.println(s);
                    break;
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

    public static Draw getInstance(){
        if(instance == null){
            instance = new Draw();
        }
        return instance;
    }


}

