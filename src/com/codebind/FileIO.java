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
            for (Shapes shape : objShapes)
            {
                if (shape.getSubordinates().size() != 0)
                {
                    groupcount++;
                }
            }

            file.write("Group " + groupcount);
            file.write(System.lineSeparator());

            for (Shapes shape : objShapes)
            {
                if (shape.getSubordinates().size() == 0)
                {
                    writeToFile(file, shape);
                }
                else
                {
                    writeToFile(file, shape);

                    for(Shapes child : shape.getSubordinates())
                    {
                        writeToFile(file, child);
                    }
                }
            }
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(FileWriter file, Shapes shape) throws IOException
    {
        //write the variables to the paint.txt
        file.write(shape.getName());
        file.write(" " + shape.getPosX());
        file.write(" " + shape.getPosY());
        file.write(" " + shape.getWidth());
        file.write(" " + shape.getHeight());
        file.write(System.lineSeparator()); //at the end of every object create a new line
    }

    public void readFile()
    {
        try { //read the lines from paint.txt
            List<String> allLines = Files.readAllLines(Paths.get("Paint.txt"));
            for (String line : allLines) {
                String[] words = line.split(" "); //split the lines in to words
                String name = words[0];
                String posX = words[1];
                String posY = words[2];
                String width = words[3];
                String height = words[4];

                //make new shapes from the words
                draw.makeShape(name, Integer.parseInt(posX), Integer.parseInt(posY), Integer.parseInt(width), Integer.parseInt(height));
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
