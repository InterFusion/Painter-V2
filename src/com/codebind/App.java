package com.codebind;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class App extends JFrame{
    private JButton cirkelButton;
    private JPanel mainPanel;
    private JButton rechthoekButton;
    private JButton ovaalButton;
    private JButton vierkantButton;
    private JPanel buttonPanel;
    private JPanel canvas;
    private JTree tree1;
    protected final Draw draw;                  //draw class
    protected final Tree tree;                  //tree class

    protected static App instance = null;

    //start the app
    public App() {
        super("Painter-V2");
        instance = this;

        //get instance of draw and set the size and location
        this.draw = Draw.getInstance();
        this.draw.setSize(1000,700);
        this.draw.setLocation(100, 0);

        tree = Tree.getInstance();

        //add jPanel to jFrame
        add(draw, BorderLayout.CENTER);
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1280,720);

        //add actionListeners to the buttons
        addActionListeners();

    }

    public void addActionListeners()
    {
        cirkelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setShape(0);
            }
        });
        ovaalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setShape(1);
            }
        });
        vierkantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setShape(2);
            }
        });
        rechthoekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setShape(3);
            }
        });
    }

    //get the instance of app
    public static App getInstance(){
        return instance;
    }

    public static void main(String[] args)
    {
        new App();
    }

    //get tree from app.form
    public JTree getTree()
    {
        return tree1;
    }
}
