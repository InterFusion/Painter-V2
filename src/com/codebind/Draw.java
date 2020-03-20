package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Draw extends JPanel
{

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Point startDrag, endDrag;

    public Draw()
    {
        repaint();
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
                Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
                shapes.add(r);
                startDrag = null;
                endDrag = null;
                repaint();
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
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setStroke(new BasicStroke(2));
        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        for (Shape s : shapes)
        {
            g2.draw(s);
            g2.setPaint(Color.RED);
            g2.fill(s);
        }

        if (startDrag != null && endDrag != null)
        {
            Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
            g2.draw(r);
        }
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2)
    {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}


