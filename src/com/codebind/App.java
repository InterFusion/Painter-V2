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
    private static DefaultMutableTreeNode root;
    private JButton cirkelButton;
    private JPanel mainPanel;
    private JButton rechthoekButton;
    private JButton ovaalButton;
    private JButton vierkantButton;
    private JPanel buttonPanel;
    private JPanel canvas;
    private JTree tree1;
    protected final Draw draw;
    protected final App instance = null;

    public App() {
        super("Painter v2");
        this.draw = Draw.getInstance();
        this.draw.setSize(1000,700);
        this.draw.setLocation(100, 0);

        add(draw, BorderLayout.CENTER);
        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1280,720);



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

    public final App getInstance(){
        return instance;
    }

    public static void main(String[] args)
    {
        new App();
    }


}
