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

public class App{
    private static DefaultMutableTreeNode root;
    private JButton cirkelButton;
    private JPanel mainPanel;
    private JButton rechthoekButton;
    private JButton ovaalButton;
    private JButton vierkantButton;
    private JPanel buttonPanel;
    private JPanel canvas;
    private JTree tree1;
    private static Draw draw;
    public static App mainapp;


    public App() {

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

        tree1.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree1.getLastSelectedPathComponent();

                if (selectedNode == null)
                    //Nothing is selected.
                    return;

                Object nodeInfo = selectedNode.getUserObject();

                if (selectedNode.isLeaf()) {
                    Shapes s = (Shapes) nodeInfo;
                    System.out.println(s.name);
                    System.out.println(s.posX);
                    System.out.println(s.posY);
                    System.out.println(s.width);
                    System.out.println(s.height);
                    s.Resize(100,100);
                }
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Painter-V2");

        draw = new Draw();
        draw.setSize(1000,700);
        draw.setLocation(100, 0);

        frame.add(draw, BorderLayout.CENTER);
        mainapp = new App();

        //create the root node
        root = new DefaultMutableTreeNode("Shapes");
        DefaultTreeModel model =(DefaultTreeModel) mainapp.tree1.getModel();
        model.setRoot(root);

        frame.add(mainapp.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1280,720);
    }

    public void addTreeNode(Shapes obj)
    {
        //add the child nodes to the root node
        root.add(new DefaultMutableTreeNode(obj));

        DefaultTreeModel model =(DefaultTreeModel) mainapp.tree1.getModel();
        model.setRoot(root);
    }

    public Draw getDraw()
    {
        return draw;
    }
}
