package com.codebind;

import UndoRedo.UndoHandler;
import UndoRedo.UndoableGroup;
import UndoRedo.UndoableGroupDelete;
import ornaments.BottomDecorator;
import ornaments.LeftDecorator;
import ornaments.RightDecorator;
import ornaments.TopDecorator;
import shapes.IShapes;
import shapes.Shapes;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.tree.*;
import javax.swing.undo.UndoManager;
import javax.xml.crypto.NodeSetData;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MenuBar extends JMenuBar implements ActionListener, UndoableEditListener
{
    private static MenuBar instance = null;         //instance of this class
    private JMenuItem m1, m2, b1, b2, g1, g2, o1;
    private JMenu menu, bewerken, groepen, ornament;
    private JDialog d;
    private ArrayList<Shapes> listOfShapes = new ArrayList<>();
    protected UndoManager undoHandler = UndoHandler.getInstance();
    protected final Tree tree;                  //tree class
    protected final FileIO fileIO;              //the FileIO class
    private DefaultTreeModel dialogTreemodel;
    private DefaultMutableTreeNode dialogTreeroot;

    //make al the buttons when the program starts
    private MenuBar(){
        menu = new JMenu("File");
        bewerken = new JMenu("Bewerken");
        groepen = new JMenu("Groepen");
        ornament = new JMenu("Ornament");

        m1 = new JMenuItem("Opslaan");
        m2 = new JMenuItem("Openen");

        b1 = new JMenuItem("Verplaatsen");
        b2 = new JMenuItem("Grootte aanpassen");

        g1 = new JMenuItem("Groep aanmaken");
        g2 = new JMenuItem("Groep verwijderen");

        o1 = new JMenuItem("Ornament toevoegen");

        menu.add(m1);
        menu.add(m2);

        bewerken.add(b1);
        bewerken.add(b2);

        groepen.add(g1);
        groepen.add(g2);

        ornament.add(o1);

        add(menu);
        add(bewerken);
        add(groepen);
        add(ornament);

        tree = Tree.getInstance();
        fileIO = FileIO.getInstance();
        addActionListeners();
    }

    public void addActionListeners() {
        m1.addActionListener(this);
        m2.addActionListener(this);

        b1.addActionListener(this);
        b2.addActionListener(this);

        g1.addActionListener(this);
        g2.addActionListener(this);

        o1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();

        //switch to see which button is pressed
        switch (s)
        {
            case "Grootte aanpassen":
                if (tree.getSelectedShape() != null)
                {
                    JTextField field1 = new JTextField(Integer.toString(tree.getSelectedShape().getWidth()));
                    JTextField field2 = new JTextField(Integer.toString(tree.getSelectedShape().getHeight()));
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Breedte:"));
                    panel.add(field1);
                    panel.add(new JLabel("Hoogte:"));
                    panel.add(field2);
                    int result = JOptionPane.showConfirmDialog(null, panel, "Grootte aanpassen",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION)
                    {
                        tree.getSelectedShape().refactor(tree.getSelectedShape().getPosX(), tree.getSelectedShape().getPosY(), Integer.parseInt(field1.getText()), Integer.parseInt(field2.getText()));
                    }
                }
                break;
            case "Verplaatsen":
                if (tree.getSelectedShape() != null)
                {
                    JTextField posx = new JTextField(Integer.toString(tree.getSelectedShape().getPosX()));
                    JTextField posy = new JTextField(Integer.toString(tree.getSelectedShape().getPosY()));
                    JPanel panel1 = new JPanel(new GridLayout(0, 1));
                    panel1.add(new JLabel("Pos x:"));
                    panel1.add(posx);
                    panel1.add(new JLabel("Pos y:"));
                    panel1.add(posy);
                    int result1 = JOptionPane.showConfirmDialog(null, panel1, "Verplaatsen",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result1 == JOptionPane.OK_OPTION)
                    {
                        new TopDecorator(tree.getSelectedShape());
                    }
                }
                break;
            case "Opslaan":
                fileIO.createFile();
                break;
            case "Openen":
                fileIO.readFile();
                break;
            case "Groep aanmaken":
                if (tree.getSelectedShape() != null)
                {
                    openDialog(tree.getSelectedNode(), tree.getSelectedShape());
                }
                break;
            case "Groep verwijderen":
                if (tree.getSelectedShape() != null)
                {
                    undoHandler.undoableEditHappened(new UndoableEditEvent(
                            this, new UndoableGroupDelete(tree.getSelectedShape(), tree.getSelectedShape().getSubordinates()))
                    );
                    tree.getSelectedShape().getSubordinates().clear();
                    tree.updateTree();
                }
                break;
            case "Ornament toevoegen":
                if (tree.getSelectedShape() != null)
                {
                    String[] ornamentStrings = {"Top", "Bottom", "Left", "Right"};
                    JComboBox posO = new JComboBox(ornamentStrings);
                    JTextField nameO = new JTextField();
                    JPanel panel1 = new JPanel(new GridLayout(0, 1));
                    panel1.add(new JLabel("Position Ornament:"));
                    panel1.add(posO);
                    panel1.add(new JLabel("Ornament"));
                    panel1.add(nameO);
                    int result1 = JOptionPane.showConfirmDialog(null, panel1, "Verplaatsen",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    int selection = posO.getSelectedIndex();
                    String text = nameO.getText();
                    if (result1 == JOptionPane.OK_OPTION)
                    {
                        switch(selection){
                            case 0:
                                new TopDecorator(tree.getSelectedShape()).setOrnament(ornamentStrings[selection], text);
                                break;
                            case 1:
                                new BottomDecorator(tree.getSelectedShape()).setOrnament(ornamentStrings[selection], text);
                                break;
                            case 2:
                                new LeftDecorator(tree.getSelectedShape()).setOrnament(ornamentStrings[selection], text);
                                break;
                            case 3:
                                new RightDecorator(tree.getSelectedShape()).setOrnament(ornamentStrings[selection], text);
                                break;
                        }
                    }
                }
                break;
        }
    }
    public void openDialog(DefaultMutableTreeNode firstSelectedNode, Shapes firstSelectedShape)
    {
        JFrame f = new JFrame();
        d = new JDialog(f, "dialog Box");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // create a label
        JPanel test = new JPanel();
        JTree dialogTree = new JTree();

        dialogTreemodel =(DefaultTreeModel) dialogTree.getModel();
        dialogTreemodel.setRoot((TreeNode) tree.getModel().getRoot());
        dialogTreeroot = (DefaultMutableTreeNode) dialogTreemodel.getRoot();

        if(firstSelectedNode.getParent() != null)
        {
            DefaultMutableTreeNode firstSelectedNodeParent = (DefaultMutableTreeNode) firstSelectedNode.getParent();
            firstSelectedNodeParent.remove(firstSelectedNode);
        }
        else
        {
             dialogTreeroot.remove(firstSelectedNode);
        }

        dialogTreemodel.reload(dialogTreeroot);

        d.add(test);
        test.add(okButton);
        test.add(cancelButton);
        test.add(dialogTree);

        // setsize of dialog
        d.setSize(400, 400);
        // set visibility of dialog
        d.setVisible(true);

        okButton.addActionListener(e -> makeGroup(firstSelectedShape, listOfShapes));
        cancelButton.addActionListener(e -> cancel());

        dialogTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dialogTree.getLastSelectedPathComponent();

                //Nothing is selected.
                if (selectedNode == null)
                    return;

                Object nodeInfo = selectedNode.getUserObject();
                listOfShapes.add((Shapes) nodeInfo);
                if(selectedNode.getParent() != null)
                {
                    DefaultMutableTreeNode selectedNodeParent = (DefaultMutableTreeNode) selectedNode.getParent();
                    selectedNodeParent.remove(selectedNode);
                }
                else
                {
                    dialogTreeroot.remove(selectedNode);
                }

                dialogTreemodel.reload(dialogTreeroot);
            }
        });
    }

    public void makeGroup(Shapes firstSelectedShape, ArrayList<Shapes> listOfShapes)
    {
        if(listOfShapes != null)
        {
            for (Shapes s: listOfShapes)
            {
                firstSelectedShape.addSubordinates(s);
                undoHandler.undoableEditHappened(new UndoableEditEvent(
                        firstSelectedShape, new UndoableGroup(firstSelectedShape, s))
                );
                tree.updateTree();
            }
            tree.updateTree();
            d.dispose();
        }
    }

    public void cancel(){
        if(listOfShapes != null){
            listOfShapes.clear();
            d.dispose();
        }
    }

    public static MenuBar getInstance(){
        if(instance == null)
            instance = new MenuBar();
        return instance;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e)
    {
        undoHandler.addEdit(e.getEdit());
    }
}


