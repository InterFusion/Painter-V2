package shapes;

import UndoRedo.UndoHandler;
import UndoRedo.UndoableRefactor;
import com.codebind.Draw;
import com.codebind.Tree;
import ornaments.IShapeDecorator;

import javax.swing.event.UndoableEditEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.util.ArrayList;

public class Shapes
{
    private IShapes iShapes;
    private final Draw draw;
    private UndoManager undoHandler = UndoHandler.getInstance();

    //create a shape
    public Shapes(IShapes iShapes) {
        this.iShapes = iShapes;
        draw = Draw.getInstance();
        draw.setObjShapes(this);
        iShapes.setTreeNode(this);
        Tree.getInstance().updateTree();
    }

    public void refactor(int posX, int posY, int width, int height) {
        iShapes.refactor(posX, posY, width, height);
        //add action to undoHandler
        undoHandler.undoableEditHappened(new UndoableEditEvent(
                this, new UndoableRefactor(this)
        ));
    }

    public void addSubordinates(Shapes e) {
        iShapes.addSubordinates(e);
    }

    public ArrayList<Shapes> getSubordinates(){
        return iShapes.getSubordinates();
    }

    public void setColor(Color color) {
        iShapes.setColor(color);
    }

    public Color getColor()
    {
        return iShapes.getColor();
    }

    public int getHeight(){
        return iShapes.getHeight();
    }

    public int getWidth(){
        return iShapes.getWidth();
    }

    public int getPosX(){
        return iShapes.getPosX();
    }

    public int getPosY(){
        return iShapes.getPosY();
    }

    public String getName(){
        return iShapes.getName();
    }

    public Shape getShape() {
        return iShapes.getShape();
    }

    public void setShape(Shape shape) {
        iShapes.setShape(shape);
    }

    public void setOldShapes(Shape shape){
        iShapes.setOldShapes(shape);
    }

    public ArrayList<Shape> getOldShapes(){
        return iShapes.getOldShapes();
    }

    public void setSubordinatesList(ArrayList<Shapes> subordinates){
        iShapes.setSubordinatesList(subordinates);
    }

    public void setboolTree(boolean isSetInTree) {
        iShapes.setboolTree(isSetInTree);
    }

    public boolean getboolTree() {
        return iShapes.getboolTree();
    }

    public DefaultMutableTreeNode getTreeNode() {
        return iShapes.getTreeNode();
    }

    public void setOrnament(String position, String text) {
        iShapes.setOrnament(position, text);
    }

    public String getPosition(){
        return iShapes.getPosition();
    }

    public String getText(){
        return iShapes.getText();
    }

    public ArrayList<String> getPList()
    {
        return iShapes.getPList();
    }

    public ArrayList<String> getTList()
    {
        return iShapes.getTList();
    }

    public void deleteOrnament(String position, String text)
    {
        iShapes.deleteOrnament(position, text);
    }
}
