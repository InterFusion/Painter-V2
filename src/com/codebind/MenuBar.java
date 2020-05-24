package com.codebind;

import UndoRedo.UndoHandler;
import UndoRedo.UndoableGroup;
import UndoRedo.UndoableGroupDelete;
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
    private JMenuItem m1, m2, b1, b2, g1, g2;
    private JMenu menu, bewerken, groepen;
    private JDialog d;
    private ArrayList<Shapes> listOfShapes = new ArrayList<>();
    protected UndoManager undoHandler = UndoHandler.getInstance();
    protected final Tree tree;                  //tree class
    protected final FileIO fileIO;              //the FileIO class
    private DefaultTreeModel dialogTreemodel;
    private DefaultMutableTreeNode dialogTreeroot;

    //make al the buttons when the program starts
    public MenuBar(){
        menu = new JMenu("File");
        bewerken = new JMenu("Bewerken");
        groepen = new JMenu("Groepen");

        m1 = new JMenuItem("Opslaan");
        m2 = new JMenuItem("Openen");

        b1 = new JMenuItem("Verplaatsen");
        b2 = new JMenuItem("Grootte aanpassen");

        g1 = new JMenuItem("Groep aanmaken");
        g2 = new JMenuItem("Groep verwijderen");

        menu.add(m1);
        menu.add(m2);

        bewerken.add(b1);
        bewerken.add(b2);

        groepen.add(g1);
        groepen.add(g2);

        add(menu);
        add(bewerken);
        add(groepen);

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
            case"Groep aanmaken":
                if(tree.getSelectedShape() != null)
                {
                    openDialog(tree.getSelectedNode(), tree.getSelectedShape());
                }
                break;
            case"Groep verwijderen":
                if(tree.getSelectedShape() != null)
                {
                    //create a action for the undo and redo
                    undoHandler.undoableEditHappened(new UndoableEditEvent(
                            this, new UndoableGroupDelete(tree.getSelectedShape(), tree.getSelectedShape().getSubordinates()))
                    );
                    //remove all the child of the selectedshape
                    tree.getSelectedShape().getSubordinates().clear();
                    tree.updateTree();
                }
                break;
        }
    }

    //method for creating a group
    public void openDialog(DefaultMutableTreeNode firstSelectedNode, Shapes firstSelectedShape)
    {
        JFrame f = new JFrame();                            //makes a new Jframe where everything is in
        d = new JDialog(f, "dialog Box");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // create a label
        JPanel test = new JPanel();
        JTree dialogTree = new JTree();                     // create a tree

        dialogTreemodel =(DefaultTreeModel) dialogTree.getModel();
        dialogTreemodel.setRoot((TreeNode) tree.getModel().getRoot());//clone the main tree in this tree
        dialogTreeroot = (DefaultMutableTreeNode) dialogTreemodel.getRoot();

        if(firstSelectedNode.getParent() != null)
        {
            DefaultMutableTreeNode firstSelectedNodeParent = (DefaultMutableTreeNode) firstSelectedNode.getParent();
            firstSelectedNodeParent.remove(firstSelectedNode);          //remove the selectednode from the parent
        }
        else
        {
             dialogTreeroot.remove(firstSelectedNode);                   //remove the selectednode
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

        //add actionlisteners to the buttons
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
                    selectedNodeParent.remove(selectedNode);//remove the selectednode from parent
                }
                else
                {
                    dialogTreeroot.remove(selectedNode);//remove the selectednode from tree
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
                //add every shape from the list to the parent(firstselectedshape)
                firstSelectedShape.addSubordinates(s);
                //create a new action for the undohandler
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


