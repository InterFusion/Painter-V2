package com.codebind;

import shapes.Circle;
import shapes.Ellipse;
import shapes.Rectangle;
import shapes.Shapes;
import shapes.Square;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import UndoRedo.*;

public class Draw extends JPanel
{
    private ArrayList<Shapes> objShapes = new ArrayList<Shapes>();          //list of objects
    private Point startDrag, endDrag;                               //start and endpoint
    private int shapeInt = 0;                                       //select which shape to draw

    protected JButton undoButton = new JButton("Undo");
    protected JButton redoButton = new JButton("Redo");
    protected UndoManager undoHandler = UndoHandler.getInstance();

    private static Draw instance = null;

    private Draw()
    {
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        add(buttonPanel, BorderLayout.NORTH);

        addActionListeners();
    }

    public void addActionListeners()
    {
        undoButton.addActionListener(e ->
        {
            try {
                undoHandler.undo();
            } catch (CannotUndoException cre) {
                cre.printStackTrace();
            }
            repaint();
            undoButton.setEnabled(undoHandler.canUndo());
            redoButton.setEnabled(undoHandler.canRedo());
        });

        redoButton.addActionListener(e ->
        {
            try {
                undoHandler.redo();
            } catch (CannotRedoException cre) {
                cre.printStackTrace();
            }
            repaint();
            undoButton.setEnabled(undoHandler.canUndo());
            redoButton.setEnabled(undoHandler.canRedo());
        });

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
                if(endDrag != startDrag)
                {
                    Shapes s = null;

                    if(shapeInt == 0)
                        s = new Shapes(new Circle("Circle",  Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.y - e.getY()), Math.abs(startDrag.y - e.getY())));
                    else if(shapeInt == 1)
                        s = new Shapes(new Ellipse("Ellipse", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY())));
                    else if(shapeInt == 2)
                        s = new Shapes(new Square("Square", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.y - e.getY()), Math.abs(startDrag.y - e.getY())));
                    else if(shapeInt == 3)
                        s = new Shapes(new Rectangle("Rectangle", Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY())));

                    //objShapes.add(s);
                    assert s != null;
                    undoHandler.undoableEditHappened(new UndoableEditEvent(
                            this, new UndoableDraw(objShapes, s)
                    ));
                }
                undoButton.setEnabled(undoHandler.canUndo());
                redoButton.setEnabled(undoHandler.canRedo());
                repaint();

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
        super.paintComponent(g);
        draw(g);
    }

    public ArrayList<Shapes> getObjShapes() {
        return objShapes;
    }

    //will draw whats necessary to Graphics object
    private void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for (Shapes s : objShapes)
        {
            g2.setColor(s.getColor());
            g2.fill(s.getShape());
            HashMap<String, String> posText = s.getPosText(); 
            if(posText != null)
            {
                for (String position: posText.keySet())
                {
                    g2.drawString(posText.get(position), getCoordsX(s, g, posText.get(position), position), getCoordsY(s, position));
                }
            }
        }
    }

    private int getCoordsX(Shapes shape, Graphics g, String text, String pos){
        int x = shape.getPosX();
        int width = shape.getWidth();
        int textWidth = g.getFontMetrics().stringWidth(text);
        switch(pos){
            case "Top":
            case "Bottom":
                return x+(width - textWidth)/2;
            case "Left":
                return x - 30;
            case "Right":
                return (x+width) + 15;
        }
        return x;
    }

    private int getCoordsY(Shapes shape,String pos){
        int y = shape.getPosY();
        int height = shape.getHeight();
        switch(pos){
            case "Top":
                return y - (y/80);
            case "Bottom":
                return (y+height)+15;
            case "Left":
            case "Right":
                return y+(height/2);
        }
        return y;
    }
    
    public void setShape(int x)
    {
        shapeInt = x;
    }

    //method for when you load a saved file.
    public Shapes makeShape(String name, int posX, int posY, int width, int height)
    {
        Shapes s = null;
        switch (name)
                {
                    case "Circle" -> s = new Shapes(new Circle("Circle", posX, posY, width, height));
                    case "Ellipse" -> s = new Shapes(new Ellipse("Ellipse", posX, posY, width, height));
                    case "Square" -> s = new Shapes(new Square("Square", posX, posY, width, height));
                    case "Rectangle" -> s = new Shapes(new Rectangle("Rectangle", posX, posY, width, height));
                };

        repaint();
        return s;
    }

    public void setObjShapes(Shapes s){
        objShapes.add(s);
    }
    public static Draw getInstance(){
        if(instance == null){
            instance = new Draw();
        }
        return instance;
    }

}

