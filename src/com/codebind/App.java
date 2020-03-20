package com.codebind;

import com.sun.deploy.panel.JreDialog;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.io.Console;

public class App{
    private JButton cirkelButton;
    private JPanel mainPanel;
    private JButton rechthoekButton;
    private JButton ovaalButton;
    private JButton vierkantButton;
    private JPanel buttonPanel;
    private JPanel canvas;
    private JTree tree1;

    public App() {

    }

    public static void main(String[] args)
    {
        Draw app = new Draw();
        JFrame frame = new JFrame("Painter-V2");
        app.setSize(1000,700);
        app.setLocation(100, 0);
        app.setBackground(Color.black);

        frame.getContentPane().add(app, BorderLayout.CENTER);
       // frame.setContentPane(new App().mainPanel);
        frame.getContentPane().add(new App().mainPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.add(new Draw());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1280,720);
    }
}
