package com.codebind;
import shapes.Shapes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileIO
{
    protected final Draw draw;                                          //draw class
    private ArrayList<Shapes> objShapes;        //list of objects

    private static FileIO instance = null;                              //the instance of this class

    public  FileIO()
    {
        objShapes = new ArrayList<>();
        draw = Draw.getInstance();
    }

    public void createFile()
    {
        //create new file paint.txt
        try (FileWriter file = new FileWriter("Paint.txt"))
        {
            String exportString = ExportVisitor.getInstance().export();
            file.write(exportString);

            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile()
    {
        draw.getObjShapes().clear();
        String name;
        int posX = 0;
        int posY = 0;
        int width = 0;
        int height = 0;
        Shapes shape = null;

        try { //read the lines from paint.txt
            List<String> allLines = Files.readAllLines(Paths.get("Paint.txt"));
            for (String line : allLines) {
                String[] words = line.split(" "); //split the lines in to words

                name = words[0];
                if(!name.contains("Group"))
                {
                    posX = Integer.parseInt(words[1]);
                    posY = Integer.parseInt(words[2]);
                    width = Integer.parseInt(words[3]);
                    height = Integer.parseInt(words[4]);

                    //make new shapes from the words
                    draw.makeShape(name.trim(), posX, posY, width, height);

                    if(shape != null && draw.getObjShapes().size() != 0)
                    {
                        //add child to the parent
                        shape.addSubordinates(draw.getObjShapes().get(draw.getObjShapes().size() - 1));
                        Tree.getInstance().updateTree();
                    }
                }
                else
                {
                    if(draw.getObjShapes().size() != 0)
                        shape = draw.getObjShapes().get(draw.getObjShapes().size() - 1);
                }
            }
            Tree.getInstance().updateTree();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileIO getInstance(){
        if(instance == null){
            instance = new FileIO();
        }
        return instance;
    }
}
