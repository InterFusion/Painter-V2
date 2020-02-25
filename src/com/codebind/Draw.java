package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Draw extends JPanel {

    private int oldX, oldY, newX, newY;

    public Draw()
    {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                newX = e.getX();
                newY = e.getY();
                drawShape();
            }
        });
    }

    private void drawShape()
    {
        repaint(oldX,oldY,newX - oldX, newY - oldY);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(oldX,oldY,newX - oldX, newY - oldY);
    }
}
