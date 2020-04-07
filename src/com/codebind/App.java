package com.codebind;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class App{
    private JButton cirkelButton;
    private JPanel mainPanel;
    private JButton rechthoekButton;
    private JButton ovaalButton;
    private JButton vierkantButton;
    private JPanel buttonPanel;
    private JPanel canvas;
    private JTree tree1;
    private static Draw app;


    public App() {

        cirkelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setShape(0);
            }
        });
        ovaalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setShape(1);
            }
        });
        vierkantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setShape(2);
            }
        });
        rechthoekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setShape(3);
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Painter-V2");

        app = new Draw();
        app.setSize(1000,700);
        app.setLocation(100, 0);

        frame.add(app, BorderLayout.CENTER);
        App mainapp = new App();
        app.setMainapp(mainapp);

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Shapes");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vierkant1");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Cirkel1");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);

        DefaultTreeModel model =(DefaultTreeModel) mainapp.tree1.getModel();
        model.setRoot(root);
        mainapp.tree1.setDragEnabled(true);


        frame.add(mainapp.mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1280,720);
    }
}
