package com.codebind;

import jdk.jfr.Event;
import shapes.Circle;
import shapes.Ellipse;
import shapes.Rectangle;
import shapes.Shapes;
import shapes.Square;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;
import java.util.ArrayList;



public class Draw extends JPanel implements UndoableEditListener
{
    private ArrayList<Shapes> objShapes = new ArrayList<Shapes>();          //list of objects
    private ArrayList<Shape> shapes = new ArrayList<Shape>();           //list to draw
    private Point startDrag, endDrag;                               //start and endpoint
    private int shapeInt = 0;                                       //select which shape to draw

    protected JButton undoButton = new JButton("Undo");
    protected JButton redoButton = new JButton("Redo");
    protected UndoManager undoHandler = UndoHandler.getInstance();

    private static Draw instance = null;

    public Draw()
    {
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        add(buttonPanel, BorderLayout.NORTH);

        undoButton.addActionListener(e ->
        {
            try {
                undoHandler.undo();
            } catch (CannotRedoException cre) {
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
                if(endDrag != startDrag)
                {
                    Shapes s = null;

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
                    undoHandler.undoableEditHappened(new UndoableEditEvent(
                            this, new UndoableDraw(shapes, s)
                    ));
                    shapes.add(s.shape);
                    System.out.println(s);
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

    //will draw whats necessary to Graphics object
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for (Shape s : shapes)
        {
            g2.draw(s);
            g2.setColor(Color.RED);
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
        // TODO: 20/04/2020 Code aanpassen dat de coordinaten ergens anders worden bepaald
        //  zodat die aangepast kan worden per object. Dan hoeven we niet elke keer de objecten te verwijderen.
        shapes.remove(oldShape);
        shapes.add(newShape);
        repaint();
    }

    public ArrayList<Shapes> getObjShapes() {
        return objShapes;
    }

    //method for when you load a saved file.
    public void makeShape(String name, int posX, int posY, int width, int height)
    {
        Shapes s = switch (name)
                {
                    case "Circle" -> new Circle("Circle", posX, posY, width, height);
                    case "Ellipse" -> new Ellipse("Ellipse", posX, posY, width, height);
                    case "Square" -> new Square("Square", posX, posY, width, height);
                    case "Rectangle" -> new Rectangle("Rectangle", posX, posY, width, height);
                    default -> null;
                };

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

    @Override
    public void undoableEditHappened(UndoableEditEvent e)
    {
        undoHandler.addEdit(e.getEdit());
    }
}

