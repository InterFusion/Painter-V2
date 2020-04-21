package shapes;

import com.codebind.Draw;
import com.codebind.Tree;

import java.awt.*;

public class Shapes
{
    protected String name;
    protected int posX, posY, width, height;
    public Shape shape;
    protected Draw draw;
    protected Tree tree;

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
        tree.addTreeNode(this);
    }

    public void refactor(int posX, int posY, int width, int height)
    {

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
}
