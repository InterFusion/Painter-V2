package shapes;

import com.codebind.Draw;
import com.codebind.Tree;
import com.codebind.UndoHandler;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.UndoManager;
import java.awt.*;

public class Shapes
{
    public String name;
    public int posX, posY, width, height;
    public Shape shape;
    public Draw draw;
    public Tree tree;
    public DefaultMutableTreeNode treeNode;
    protected UndoManager undoHandler = UndoHandler.getInstance();
    public Shape oldShape;

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
}
