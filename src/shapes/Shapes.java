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

    protected Shape shape, oldShape;

    protected final Draw draw;
    private final Tree tree;

    private DefaultMutableTreeNode treeNode;
    private boolean isSetInTree;

    protected UndoManager undoHandler = UndoHandler.getInstance();

    protected Color color = Color.RED;



    protected ArrayList<Shapes> subordinates;


    //create a shape
    public Shapes(String name, int posX, int posY, int width, int height) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        draw = Draw.getInstance();
        tree = Tree.getInstance();
        subordinates = new ArrayList<>();
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

    public Shape getOldShape() {
        return oldShape;
    }

    public void setSubordinatesList(ArrayList<Shapes> subordinates){
        this.subordinates = subordinates;
    }

    public void setOldShape(Shape oldShape) {
        this.oldShape = oldShape;
    }
    
    public DefaultMutableTreeNode getTreeNode() {
        return treeNode;
    }

    public void setboolTree(boolean isSetInTree)
    {
        this.isSetInTree = isSetInTree;
    }

    public boolean getboolTree()
    {
        return isSetInTree;
    }

}
