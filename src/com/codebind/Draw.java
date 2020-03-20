package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Draw extends JPanel {

    private int oldX, oldY, newX, newY;

    public Draw()
    {
        this.setSize(720,480);
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                newX = e.getX();
                newY = e.getY();
                drawShape();
            }
        });
    }

    private void drawShape()
    {
        if((newX - oldX) < 0)
            repaint(newX,newY,Math.abs(newX - oldX), Math.abs(newY - oldY));
        else
            repaint(oldX,oldY,Math.abs(newX - oldX), Math.abs(newY - oldY));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        if((newX - oldX) < 0)
            g.fillRect(newX,newY,Math.abs(newX - oldX), Math.abs(newY - oldY));
        else
            g.fillRect(oldX,oldY,Math.abs(newX - oldX), Math.abs(newY - oldY));

    }
}
