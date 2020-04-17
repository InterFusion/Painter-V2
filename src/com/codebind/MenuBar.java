package com.codebind;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener
{
    private static MenuBar instance = null;
    private JMenuItem m1, m2, m3, s1, s2, b1, b2;
    private JMenu menu, submenu, bewerken;

    private JLabel label;

    protected final Tree tree;                  //tree class

    public MenuBar(){
        menu = new JMenu("Menu");
        submenu = new JMenu("Settings");
        bewerken = new JMenu(("Bewerken"));
        label = new JLabel("Hmmmmm");
        m1 = new JMenuItem("MenuItem1");
        m2 = new JMenuItem("MenuItem2");
        m3 = new JMenuItem("MenuItem3");
        s1 = new JMenuItem("SubMenuItem1");
        s2 = new JMenuItem("SubMenuItem2");

        b1 = new JMenuItem("Verplaatsen");
        b2 = new JMenuItem("Grootte aanpassen");

        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        submenu.add(s1);
        submenu.add(s2);

        bewerken.add(b1);
        bewerken.add(b2);

        add(menu);
        menu.add(submenu);
        add(bewerken);
        add(label);

        tree = Tree.getInstance();
        addActionListeners();
    }

    public void addActionListeners()
    {
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        s1.addActionListener(this);
        s2.addActionListener(this);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();

        label.setText(s + " selected");

        switch (s)
        {
            case "Grootte aanpassen":
                JTextField field1 = new JTextField(Integer.toString(tree.getSelectedShape().width));
                JTextField field2 = new JTextField(Integer.toString(tree.getSelectedShape().height));
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Breedte:"));
                panel.add(field1);
                panel.add(new JLabel("Hoogte:"));
                panel.add(field2);
                int result = JOptionPane.showConfirmDialog(null, panel, "Grootte aanpassen",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    tree.getSelectedShape().refactor(tree.getSelectedShape().posX, tree.getSelectedShape().posY, Integer.parseInt(field1.getText()),Integer.parseInt(field2.getText()));
                }
                break;
            case "Verplaatsen":
                JTextField posx = new JTextField(Integer.toString(tree.getSelectedShape().posX));
                JTextField posy = new JTextField(Integer.toString(tree.getSelectedShape().posY));
                JPanel panel1 = new JPanel(new GridLayout(0, 1));
                panel1.add(new JLabel("Pos x:"));
                panel1.add(posx);
                panel1.add(new JLabel("Pos y:"));
                panel1.add(posy);
                int result1 = JOptionPane.showConfirmDialog(null, panel1, "Verplaatsen",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result1 == JOptionPane.OK_OPTION) {
                    tree.getSelectedShape().refactor(Integer.parseInt(posx.getText()), Integer.parseInt(posy.getText()), tree.getSelectedShape().width, tree.getSelectedShape().height);
                }
                break;
        }
    }

    public JLabel getLabel(){
        return label;
    }

    public static MenuBar getInstance(){
        if(instance == null)
            instance = new MenuBar();
        return instance;
    }
}


