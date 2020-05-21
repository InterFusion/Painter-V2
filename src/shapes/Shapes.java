package shapes;

import com.codebind.Draw;
import com.codebind.Tree;
import com.codebind.UndoHandler;
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
    protected final Tree tree;
    protected DefaultMutableTreeNode treeNode;

    protected Color color = Color.RED;

    protected UndoManager undoHandler = UndoHandler.getInstance();

    //create a shape
    public Shapes(String name, int posX, int posY, int width, int height) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.draw = Draw.getInstance();
        tree = Tree.getInstance();
        treeNode = new DefaultMutableTreeNode(this);            //creates a treenode for the tree
        draw.setObjShapes(this);
        tree.updateTree();
    }

    public void refactor(int posX, int posY, int width, int height) {

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

    public ArrayList<Shape> getOldShapes(){
        return oldShapes;
    }

    public DefaultMutableTreeNode getTreeNode() {
        return treeNode;
    }
}
