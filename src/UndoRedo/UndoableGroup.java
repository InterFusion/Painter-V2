package UndoRedo;

import com.codebind.Tree;
import shapes.IShapes;
import shapes.Shapes;

import javax.swing.undo.AbstractUndoableEdit;

public class UndoableGroup extends AbstractUndoableEdit
{
    private Shapes shapes;
    private Shapes addedShape;

    public UndoableGroup(Shapes shapes, Shapes addedShape){
        this.shapes = shapes;
        this.addedShape = addedShape;
    }

    public void undo(){
        super.undo();
        shapes.getSubordinates().remove(addedShape);
        Tree.getInstance().updateTree();
    }

    public void redo(){
        super.redo();
        shapes.getSubordinates().add(addedShape);
        Tree.getInstance().updateTree();
    }
}
