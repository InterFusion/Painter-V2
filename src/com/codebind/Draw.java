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

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Point startDrag, endDrag;
    private int Shapeint = 0;
    private App mainapp;

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
                    Shape r = makeCircle(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                }
                else if(Shapeint == 1)
                {
                    Shape r = makeEllipse(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                }
                else if(Shapeint == 2)
                {
                    Shape r =  makeSquare(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                }
                else if(Shapeint == 3)
                {
                    Shapes test = new Rectangle("Rectangle", startDrag.x, startDrag.y, e.getX(), e.getY());
                    Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);

                    System.out.println(test);
                }
               // repaint(startDrag.x,startDrag.y, e.getX() - startDrag.x, e.getY() - startDrag.y);

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
                //repaint();
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

    public void setMainapp(App app)
    {
        mainapp = app;
    }
}


