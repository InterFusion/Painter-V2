package com.codebind;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

public class MenuBar extends JMenuBar implements ActionListener
{
    private static MenuBar instance = null;         //instance of this class
    private JMenuItem m1, m2, b1, b2;
    private JMenu menu, bewerken;

    protected final Tree tree;                  //tree class
    protected final FileIO fileIO;              //the FileIO class

    //make al the buttons when the program starts
    public MenuBar(){
        menu = new JMenu("File");
        bewerken = new JMenu(("Bewerken"));

        m1 = new JMenuItem("Opslaan");
        m2 = new JMenuItem("Openen");

        b1 = new JMenuItem("Verplaatsen");
        b2 = new JMenuItem("Grootte aanpassen");

        menu.add(m1);
        menu.add(m2);

        bewerken.add(b1);
        bewerken.add(b2);

        add(menu);
        add(bewerken);

        tree = Tree.getInstance();
        fileIO = FileIO.getInstance();
        addActionListeners();
    }

    public void addActionListeners() {
        m1.addActionListener(this);
        m2.addActionListener(this);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();

        //switch to see which button is pressed
        switch (s)
        {
            case "Grootte aanpassen":
                if(tree.getSelectedShape() != null)
                {
                    JTextField field1 = new JTextField(Integer.toString(tree.getSelectedShape().getWidth()));
                    JTextField field2 = new JTextField(Integer.toString(tree.getSelectedShape().getHeight()));
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Breedte:"));
                    panel.add(field1);
                    panel.add(new JLabel("Hoogte:"));
                    panel.add(field2);

                    //create a new JoptionPanel for the size
                    int result = JOptionPane.showConfirmDialog(null, panel, "Grootte aanpassen",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    //if selected ok get text of field1 and field2 and refactor the selected shape
                    if (result == JOptionPane.OK_OPTION) {
                        tree.getSelectedShape().refactor(tree.getSelectedShape().getPosX(), tree.getSelectedShape().getPosY(), Integer.parseInt(field1.getText()),Integer.parseInt(field2.getText()));
                    }
                }
                break;
            case "Verplaatsen":
                if(tree.getSelectedShape() != null)
                {
                    JTextField posx = new JTextField(Integer.toString(tree.getSelectedShape().getPosX()));
                    JTextField posy = new JTextField(Integer.toString(tree.getSelectedShape().getPosY()));
                    JPanel panel1 = new JPanel(new GridLayout(0, 1));
                    panel1.add(new JLabel("Pos x:"));
                    panel1.add(posx);
                    panel1.add(new JLabel("Pos y:"));
                    panel1.add(posy);

                    //create a new JoptionPanel for the position
                    int result1 = JOptionPane.showConfirmDialog(null, panel1, "Verplaatsen",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    //if selected ok get text of posx and posy and refactor the selected shape
                    if (result1 == JOptionPane.OK_OPTION) {
                        tree.getSelectedShape().refactor(Integer.parseInt(posx.getText()), Integer.parseInt(posy.getText()), tree.getSelectedShape().getWidth(), tree.getSelectedShape().getHeight());
                    }
                }
                break;
            case "Opslaan":
                fileIO.createFile();
                break;
            case"Openen":
                fileIO.readFile();
                break;
        }
    }

    public static MenuBar getInstance(){
        if(instance == null)
            instance = new MenuBar();
        return instance;
    }
}


