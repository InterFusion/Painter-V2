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
        Shapes shapes = tree.getSelectedShape();
        //switch to see which button is pressed
        switch (s)
        {
            case "Grootte aanpassen":
                if (shapes != null)
                {
                    JTextField field1 = new JTextField(Integer.toString(shapes.getWidth()));
                    JTextField field2 = new JTextField(Integer.toString(shapes.getHeight()));
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
                        shapes.refactor(shapes.getPosX(), shapes.getPosY(), Integer.parseInt(field1.getText()), Integer.parseInt(field2.getText()));
                    }
                }
                break;
            case "Verplaatsen":
                if (shapes != null)
                {
                    JTextField posx = new JTextField(Integer.toString(shapes.getPosX()));
                    JTextField posy = new JTextField(Integer.toString(shapes.getPosY()));
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
                        shapes.refactor(Integer.parseInt(posx.getText()), Integer.parseInt(posy.getText()), tree.getSelectedShape().getWidth(), tree.getSelectedShape().getHeight());
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
                if (shapes != null)
                {
                    openDialog(tree.getSelectedNode(), shapes);
                }
                break;
            case "Groep verwijderen":
                if (shapes != null)
                {
                    //create a action for the undo and redo
                    undoHandler.undoableEditHappened(new UndoableEditEvent(
                            this, new UndoableGroupDelete(shapes, shapes.getSubordinates()))
                    );
                    //remove all the child of the selectedshape
                    shapes.getSubordinates().clear();
                    tree.updateTree();
                }
                break;
            case "Ornament toevoegen":
                if (shapes != null)
                {
                    String[] ornamentStrings = {"Top", "Bottom", "Left", "Right"};
                    JComboBox posO = new JComboBox(ornamentStrings); //Dropdown menu
                    JTextField nameO = new JTextField();
                    JPanel panel1 = new JPanel(new GridLayout(0, 1));
                    panel1.add(new JLabel("Position Ornament:"));
                    panel1.add(posO);
                    panel1.add(new JLabel("Ornament"));
                    panel1.add(nameO);
                    int result1 = JOptionPane.showConfirmDialog(null, panel1, "Verplaatsen",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    int selection = posO.getSelectedIndex(); //Selected position
                    String text = nameO.getText(); //Text of the ornament

                    if (result1 == JOptionPane.OK_OPTION)
                    {
                        switch(selection){
                            case 0:
                                new TopDecorator(shapes).setOrnament(ornamentStrings[selection], text);
                                break;
                            case 1:
                                new BottomDecorator(shapes).setOrnament(ornamentStrings[selection], text);
                                break;
                            case 2:
                                new LeftDecorator(shapes).setOrnament(ornamentStrings[selection], text);
                                break;
                            case 3:
                                new RightDecorator(shapes).setOrnament(ornamentStrings[selection], text);
                                break;
                        }
                        Draw.getInstance().repaint();
                    }
                }
                break;
        }
    }

    //method for creating a group
    public void openDialog(DefaultMutableTreeNode firstSelectedNode, Shapes firstSelectedShape)
    {
        JFrame f = new JFrame();                                //makes a new Jframe where everything is in
        d = new JDialog(f, "dialog Box");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // create a label
        JPanel jPanel = new JPanel();                           //create a jpanel
        JTree dialogTree = new JTree();                         // create a tree

        dialogTreemodel =(DefaultTreeModel) dialogTree.getModel();
        dialogTreemodel.setRoot((TreeNode) tree.getModel().getRoot());      //clone the main tree in this tree
        dialogTreeroot = (DefaultMutableTreeNode) dialogTreemodel.getRoot();

        if(firstSelectedNode.getParent() != null)
        {
            //get the parent of firstselectedNode
            DefaultMutableTreeNode firstSelectedNodeParent = (DefaultMutableTreeNode) firstSelectedNode.getParent();
            firstSelectedNodeParent.remove(firstSelectedNode);      //remove the selectednode from the parent
        }
        else
        {
             dialogTreeroot.remove(firstSelectedNode);          //remove the selectednode
        }

        dialogTreemodel.reload(dialogTreeroot);

        d.add(jPanel);
        jPanel.add(okButton);
        jPanel.add(cancelButton);
        jPanel.add(dialogTree);

        // setsize of dialog
        d.setSize(400, 400);
        // set visibility of dialog
        d.setVisible(true);

        //add actionlisteners to the buttons
        okButton.addActionListener(e -> makeGroup(firstSelectedShape, listOfShapes));
        cancelButton.addActionListener(e -> cancel());

        dialogTree.addTreeSelectionListener(e ->
        {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dialogTree.getLastSelectedPathComponent();

            //Nothing is selected.
            if (selectedNode == null)
                return;

            Object nodeInfo = selectedNode.getUserObject();
            listOfShapes.add((Shapes) nodeInfo);        //add the selected node to the list
            if(selectedNode.getParent() != null)
            {
                DefaultMutableTreeNode selectedNodeParent = (DefaultMutableTreeNode) selectedNode.getParent();
                selectedNodeParent.remove(selectedNode);        //remove the selectednode from parent
            }
            else
            {
                dialogTreeroot.remove(selectedNode);            //remove the selectednode from tree
            }

            dialogTreemodel.reload(dialogTreeroot);
        });
    }

    public void makeGroup(Shapes firstSelectedShape, ArrayList<Shapes> listOfShapes)
    {
        if(listOfShapes != null)
        {
            for (Shapes s: listOfShapes)
            {
                //add every shape from the list to the parent(firstselectedshape)
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


