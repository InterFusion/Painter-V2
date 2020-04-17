package com.codebind;

import java.util.LinkedList;
import java.util.List;

public class Controller
{
    private Action currentAction = null;
    private List<Action> actions = new LinkedList<>();

    public List<Action> getActions(){
        return actions;
    }

    public void addAction(Action action){
        currentAction = action;
        actions.add(action);
    }

    public void undoAction(){

    }



}
