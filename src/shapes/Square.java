package shapes;

import com.codebind.Draw;
import com.codebind.Tree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Square implements IShapes
{
    private String name;
    private int posX, posY, width, height;
    HashMap<String, String> posText = new HashMap<>();

    private Shape shape;
    private ArrayList<Shape> oldShapes = new ArrayList<>();

    private final Draw draw;
    private final Tree tree;

    private DefaultMutableTreeNode treeNode;
    private boolean isSetInTree;

    private Color color = Color.RED;

    private ArrayList<Shapes> subordinates = new ArrayList<>();

    public Square(String name, int posX, int posY, int width, int height)
    {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        draw = Draw.getInstance();
        tree = Tree.getInstance();

        tree.updateTree();
        shape = new Rectangle2D.Float(posX, posY, width, height);
        setOldShapes(shape);
    }

    @Override
    public void refactor(int posX, int posY, int width, int height)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        Shape s = new Rectangle2D.Float(this.posX, this.posY, this.width, this.height);         //create a new shape with the paramaters
        draw.repaint();
        shape = s;
        setOldShapes(shape);
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitSquare(this);
    }

    @Override
    public void setOrnament(String position, String text)
    {
        posText.put(position, text);
    }

    @Override
    public void deleteOrnament(String position)
    {
        posText.remove(position);
    }

    @Override
    public HashMap<String, String> getPosText()
    {
        return posText;
    }

    @Override
    public void addSubordinates(Shapes e) {
        subordinates.add(e);
    }

    @Override
    public ArrayList<Shapes> getSubordinates(){
        return subordinates;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        draw.repaint();
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    @Override
    public int getHeight(){
        return height;
    }

    @Override
    public int getWidth(){
        return width;
    }

    @Override
    public int getPosX(){
        return posX;
    }

    @Override
    public int getPosY(){
        return posY;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void setOldShapes(Shape shape){
        oldShapes.add(shape);
    }

    @Override
    public ArrayList<Shape> getOldShapes(){
        return oldShapes;
    }

    @Override
    public void setSubordinatesList(ArrayList<Shapes> subordinates){
        this.subordinates = subordinates;
    }

    @Override
    public void setboolTree(boolean isSetInTree) {
        this.isSetInTree = isSetInTree;
    }

    @Override
    public boolean getboolTree() {
        return isSetInTree;
    }

    @Override
    public DefaultMutableTreeNode getTreeNode() {
        return treeNode;
    }

    @Override
    public void setTreeNode(Shapes e) {
        treeNode = new DefaultMutableTreeNode(e);
    }
}
