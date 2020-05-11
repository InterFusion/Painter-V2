package UndoRedo;

import com.codebind.MenuBar;
import com.codebind.Tree;
import shapes.IShapes;
import shapes.Shapes;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;

public class UndoableGroupDelete extends AbstractUndoableEdit
{
    private ArrayList<Shapes> subordinates = new ArrayList<>();
    private Shapes shapes;

    public UndoableGroupDelete(Shapes shapes, ArrayList<Shapes> subordinates){
        this.subordinates.addAll(subordinates);
        this.shapes = shapes;
    }

    public void undo(){
        super.undo();
        shapes.getSubordinates().addAll(subordinates);
        Tree.getInstance().updateTree();
    }

    public void redo(){
        super.redo();
        shapes.getSubordinates().clear();
        Tree.getInstance().updateTree();
    }
}
