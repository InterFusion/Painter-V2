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
            StringBuilder whiteSpace = new StringBuilder();
            TreeModel model = Tree.getInstance().getModel();        //get the root of the tree with everything in it
            if (model != null) {
                Object root = model.getRoot();
                System.out.println(root.toString());
                walk(model, root, file, whiteSpace);                //walk trough the tree
            }
            else
                System.out.println("Tree is empty.");

            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void walk(TreeModel model, Object o, FileWriter file, StringBuilder whiteSpace) throws IOException {
        int cc = model.getChildCount(o);
        file.write(whiteSpace + "Group " + cc);             //add group with the childcount
        file.write(System.lineSeparator());
        whiteSpace.append("\t");
        for( int i=0; i < cc; i++) {
            Object child = model.getChild(o, i );
            if (model.isLeaf(child)) {            //parent
                for(Shapes shape : draw.getObjShapes())
                {
                    if(shape.toString().contains(child.toString()))
                        writeToFile(file, shape, whiteSpace);
                }
            }
            else {                              //child
                for(Shapes shape : draw.getObjShapes())
                {
                    if(shape.toString().contains(child.toString()))
                        writeToFile(file, shape, whiteSpace);
                }
                walk(model, child, file, whiteSpace);
            }
        }
    }

    public void writeToFile(FileWriter file, Shapes shape, StringBuilder whitespace) throws IOException
    {
        //write the variables to the paint.txt
        file.write(whitespace + shape.getName());
        file.write(" " + shape.getPosX());
        file.write(" " + shape.getPosY());
        file.write(" " + shape.getWidth());
        file.write(" " + shape.getHeight());
        file.write(System.lineSeparator()); //at the end of every object create a new line
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
                        //add child to parent
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
