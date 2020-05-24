package com.codebind;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.undo.UndoManager;

public class App extends JFrame{
    private JButton cirkelButton;
    private JPanel mainPanel;
    private JButton rechthoekButton;
    private JButton ovaalButton;
    private JButton vierkantButton;
    private JPanel buttonPanel;
    private JPanel canvas;
    private JPanel treePanel;
    protected final Draw draw;                  //draw class
    protected final Tree tree;                  //tree class
    protected final MenuBar menu;               //MenuBar class

    protected static App instance = null;

    //start the app
    public App() {
        super("Painter-V2");
        instance = this;
        this.menu = MenuBar.getInstance();
        tree = Tree.getInstance();
        this.draw = Draw.getInstance();

        canvas.add(draw);       //add draw to the canvas panel
        treePanel.add(tree);    //add tree to the treePanel

        setJMenuBar(menu);      //add jmenu to the jframe
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1280,720);

        //add actionListeners to the buttons
        addActionListeners();

    }

    public void addActionListeners()
    {
        cirkelButton.addActionListener(e -> draw.setShape(0));
        ovaalButton.addActionListener(e -> draw.setShape(1));
        vierkantButton.addActionListener(e -> draw.setShape(2));
        rechthoekButton.addActionListener(e -> draw.setShape(3));
    }

    //get the instance of app
    public static App getInstance(){
        return instance;
    }

    public static void main(String[] args)
    {
        new App();
    }
}
