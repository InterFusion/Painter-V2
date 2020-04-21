package com.codebind;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO
{
    protected final Draw draw;
    private ArrayList<Shapes> objShapes = new ArrayList<Shapes>();          //list of objects

    private static FileIO instance = null;

    public  FileIO()
    {
        draw = Draw.getInstance();
    }

    public void createFile()
    {
        objShapes = draw.getObjShapes();

        //Write JSON file
        try (FileWriter file = new FileWriter("Paint.txt")) {
            for (Shapes shape : objShapes)
            {
                file.write(shape.name);
                file.write(" " + Integer.toString(shape.posX));
                file.write(" " + Integer.toString(shape.posY));
                file.write(" " + Integer.toString(shape.width));
                file.write(" " + Integer.toString(shape.height));
                file.write(System.lineSeparator());
            }

            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile()
    {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("Paint.txt"));
            for (String line : allLines) {
                String[] words = line.split(" ");
                String name = words[0];
                String posX = words[1];
                String posY = words[2];
                String width = words[3];
                String height = words[4];

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
