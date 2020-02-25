package com.codebind;

import com.sun.deploy.panel.JreDialog;

import javax.swing.*;

public class App {
    private JButton button1;
    private JPanel mainPanel;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Painter-V2");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1280,720);
    }
}
