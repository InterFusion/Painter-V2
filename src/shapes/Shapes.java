package shapes;

import UndoRedo.UndoableGroup;
import com.codebind.Draw;
import com.codebind.Tree;
import UndoRedo.UndoHandler;

import javax.swing.event.UndoableEditEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.util.ArrayList;

public class Shapes
{
    protected String name;
    protected int posX, posY, width, height;

    protected Shape shape;

    private ArrayList<Shape> oldShapes = new ArrayList<>();

    protected final Draw draw;
    private final Tree tree;

    private DefaultMutableTreeNode treeNode;
    private boolean isSetInTree;

    protected UndoManager undoHandler = UndoHandler.getInstance();

    protected Color color = Color.RED;

    private ArrayList<Shapes> subordinates = new ArrayList<>();

    //create a shape
    public Shapes(String name, int posX, int posY, int width, int height) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        draw = Draw.getInstance();
        tree = Tree.getInstance();
        treeNode = new DefaultMutableTreeNode(this);
        draw.setObjShapes(this);
        tree.updateTree();
    }

    public void refactor(int posX, int posY, int width, int height) {

    }

    public void addSubordinates(Shapes e) {
        subordinates.add(e);
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

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setOldShapes(Shape shape){
        oldShapes.add(shape);
    }

    public void setSubordinatesList(ArrayList<Shapes> subordinates){
        this.subordinates = subordinates;
    }

    public ArrayList<Shape> getOldShapes(){
        return oldShapes;
    }

    public void setboolTree(boolean isSetInTree) {
        this.isSetInTree = isSetInTree;
    }

    public boolean getboolTree() {
        return isSetInTree;
    }

    public DefaultMutableTreeNode getTreeNode() {
        return treeNode;
    }
}
