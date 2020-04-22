package com.codebind;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class UndoHandler extends UndoManager implements UndoableEditListener
{
    private static UndoHandler instance = null;

    public UndoHandler()
    {

    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e)
    {
        instance.addEdit(e.getEdit());
    }

    public static UndoHandler getInstance(){
        if(instance == null)
            instance = new UndoHandler();
        return instance;
    }
}
