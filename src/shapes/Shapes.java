package shapes;

import com.codebind.Draw;
import com.codebind.Tree;
import UndoRedo.UndoHandler;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.util.ArrayList;

public class Shapes
{
    protected String name;
    protected int posX, posY, width, height;

    protected Shape shape, oldShape;

    protected final Draw draw;
    protected final Tree tree;
    protected DefaultMutableTreeNode treeNode;

    protected UndoManager undoHandler = UndoHandler.getInstance();

    protected Color color = Color.RED;

    private ArrayList<Shapes> subordinates;


    //create a shape
    public Shapes(String name, int posX, int posY, int width, int height)
    {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.draw = Draw.getInstance();
        tree = Tree.getInstance();
        treeNode = new DefaultMutableTreeNode(this);
        tree.addTreeNode(treeNode);
    }

    public void refactor(int posX, int posY, int width, int height)
    {

    }

    public void addSubordinates(Shapes e) {
        subordinates.add(e);
        System.out.println("ADDED");
    }

    public void removeSubordinates(Shapes e) {
        subordinates.remove(e);
    }

    public ArrayList<Shapes> getSubordinates(){
        return subordinates;
    }

    public void setColor(Color color) {
        this.color = color;
        draw.repaint();
    }

    public Color getColor()
    {
        return color;
    }


    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public String getName(){
        return name;
    }

    public Shape getShape() {
        return shape;
    }

    public Shape getOldShape() {
        return oldShape;
    }
}
