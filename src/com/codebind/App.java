package com.codebind;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                System.out.println("test cirkel");
                app.setShape(0);
            }
        });
        ovaalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test ovaal");
                app.setShape(1);
            }
        });
        vierkantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test vierkant");
                app.setShape(2);
            }
        });
        rechthoekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test rechthoek");
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
        frame.add(new App().mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1280,720);
    }
}
