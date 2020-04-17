package com.codebind;

import org.w3c.dom.Node;
import shapes.Shapes;

import java.util.LinkedList;
import java.util.List;

public class Action
{
    private Shapes shape = null;
    private Action parent = null;
    private List<Action> children = new LinkedList<>();

    public Action getParent(){
        return parent;
    }

    public List<Action> getChildren(){
        return children;
    }

    public void addAction(Action child){
        child.parent = this;
        children.add(child);
    }

    public void undoAction(Action child){
        child.parent = null;
        children.remove(child);
    }
}
