package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Draw extends JPanel {

    private int squareX;
    private int squareY;
    private int squareW = 200;
    private int squareH = 200;

    public Draw()
    {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                moveSquare(e.getX(),e.getY());
                System.out.println("test");
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                moveSquare(e.getX(),e.getY());
            }
        });
    }

    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        //if ((squareX!=x) || (squareY!=y)) {
       // repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);

        //}
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLUE);
        g.setColor(Color.RED);
        g.fillRect(squareX,squareY,squareW,squareH);
        g.setColor(Color.BLACK);
        g.drawRect(squareX,squareY,squareW,squareH);
        System.out.print("test");
    }
}
