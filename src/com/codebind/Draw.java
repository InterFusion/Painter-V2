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
    ArrayList<Shapes> objShapes = new ArrayList<Shapes>();      //list of objects
    ArrayList<Shape> shapes = new ArrayList<Shape>();           //list to draw

    Point startDrag, endDrag;
    private int Shapeint = 0;

    public Draw()
    {
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
               // repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                if(Shapeint == 0)
                {
                    Shapes s = new Circle("Circle", startDrag.x, startDrag.y, e.getX(), e.getY());
                    Shape r = makeCircle(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                    objShapes.add(s);
                    System.out.println(s);
                }
                else if(Shapeint == 1)
                {
                    Shapes s = new Ellipse("Ellipse", startDrag.x, startDrag.y, e.getX(), e.getY());
                    Shape r = makeEllipse(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                    objShapes.add(s);
                    System.out.println(s);
                }
                else if(Shapeint == 2)
                {
                    Shapes s = new Square("Square", startDrag.x, startDrag.y, e.getX(), e.getY());
                    Shape r =  makeSquare(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                    objShapes.add(s);
                    System.out.println(s);
                }
                else if(Shapeint == 3)
                {
                    Shapes s = new Rectangle("Rectangle", startDrag.x, startDrag.y, e.getX(), e.getY());
                    Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                    objShapes.add(s);
                    System.out.println(s);
                }

                paintImmediately(0,0,1000,720);
                startDrag = null;
                endDrag = null;

            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                endDrag = new Point(e.getX(), e.getY());
            }
        });
    }

    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for (Shape s : shapes)
        {
            g2.draw(s);
            g2.setPaint(Color.RED);
            g2.fill(s);
        }
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2)
    {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Rectangle2D.Float makeSquare(int x1, int y1, int x2, int y2)
    {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private  Ellipse2D.Float makeEllipse(int x1, int y1, int x2, int y2)
    {
        return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private  Ellipse2D.Float makeCircle(int x1, int y1, int x2, int y2)
    {
        return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public void setShape(int x)
    {
        Shapeint = x;
    }
}


