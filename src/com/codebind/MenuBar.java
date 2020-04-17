package com.codebind;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener
{
    private static MenuBar instance = null;
    private JMenuItem m1, m2, m3, s1, s2;
    private JMenu menu, submenu;

    private JLabel label;

    public MenuBar(){
        menu = new JMenu("Menu");
        submenu = new JMenu("Settings");
        label = new JLabel("Hmmmmm");
        m1 = new JMenuItem("MenuItem1");
        m2 = new JMenuItem("MenuItem2");
        m3 = new JMenuItem("MenuItem3");
        s1 = new JMenuItem("SubMenuItem1");
        s2 = new JMenuItem("SubMenuItem2");


        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        submenu.add(s1);
        submenu.add(s2);

        add(menu);
        menu.add(submenu);
        add(label);

        addActionListeners();
    }

    public void addActionListeners()
    {
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        s1.addActionListener(this);
        s2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();

        label.setText(s + " selected");
    }

    public static MenuBar getInstance(){
        if(instance == null)
            instance = new MenuBar();
        return instance;
    }
}


