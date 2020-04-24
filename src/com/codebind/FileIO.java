package com.codebind;
import shapes.Shapes;

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
        objShapes = draw.getObjShapes();

        //create new file paint.txt
        try (FileWriter file = new FileWriter("Paint.txt")) {
            int groupcount = 0;
            StringBuilder whiteSpace = new StringBuilder();
            for (Shapes shape : objShapes)
            {
                if (shape.getSubordinates().size() != 0)
                {
                    groupcount = shape.getTreeNode().getChildCount();
                }
            }

            file.write("Group " + groupcount);
            file.write(System.lineSeparator());
            whiteSpace.append("\t");

            for (Shapes shape : objShapes)
            {
                if (shape.getSubordinates().size() == 0)
                {
                    writeToFile(file, shape, whiteSpace); //root
                }
                else
                {
                    writeToFile(file, shape, whiteSpace); //parent
                    file.write(whiteSpace + "Group " + shape.getTreeNode().getChildCount());
                    file.write(System.lineSeparator());
                    whiteSpace.append("\t");

                    System.out.println(shape.getSubordinates().size());

                    for(Shapes child : shape.getSubordinates())
                    {
                        writeToFile(file, child, whiteSpace); //childs
                    }
                }
            }
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
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
        String name;
        int posX = 0;
        int posY = 0;
        int width = 0;
        int height = 0;

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
                }

                if(!name.contains("Group"))
                {
                    //make new shapes from the words
                    draw.makeShape(name.trim(), posX, posY, width, height);
                }
            }
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
