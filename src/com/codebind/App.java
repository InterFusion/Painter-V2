package com.codebind;

import com.sun.deploy.panel.JreDialog;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.io.Console;

public class App extends JPanel{
    private JButton cirkelButton;
    private JPanel mainPanel;
    private JButton rechthoekButton;
    private JButton ovaalButton;
    private JButton vierkantButton;
    private JPanel buttonPanel;
    private JPanel canvas;
    private JTree tree1;


    public App() {
        //canvas.add(new Draw());
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Painter-V2");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Draw());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1280,720);

    }


}
