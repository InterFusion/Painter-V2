package shapes;

import com.codebind.Draw;
import com.codebind.Tree;
import com.codebind.UndoHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.UndoManager;
import java.awt.*;

public class Shapes
{
    protected String name;
    protected int posX, posY, width, height;

    protected Shape shape, oldShape;

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
        treeNode = new DefaultMutableTreeNode(this);
        tree.addTreeNode(treeNode);
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

    public Shape getOldShape() {
        return oldShape;
    }

    public void setOldShape(Shape oldShape) {
        this.oldShape = oldShape;
    }
}
