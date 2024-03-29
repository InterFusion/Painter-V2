package shapes;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface IShapes
{
    void refactor(int posX, int posY, int width, int height);
    void addSubordinates(Shapes e);
    ArrayList<Shapes> getSubordinates();
    void setColor(Color color);
    Color getColor();
    int getHeight();
    int getWidth();
    int getPosX();
    int getPosY();
    String getName();
    Shape getShape();
    void setShape(Shape shape);
    void setOldShapes(Shape shape);
    ArrayList<Shape> getOldShapes();
    void setSubordinatesList(ArrayList<Shapes> subordinates);
    void setboolTree(boolean isSetInTree);
    boolean getboolTree();
    DefaultMutableTreeNode getTreeNode();
    void setTreeNode(Shapes shapes);
    String accept(Visitor visitor);
    void setOrnament(String position, String text);
    void deleteOrnament(String position);
    HashMap<String, String> getPosText();
}
