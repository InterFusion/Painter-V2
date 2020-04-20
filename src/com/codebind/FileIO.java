package com.codebind;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        try (FileReader reader = new FileReader("Paint.txt"))
        {
            String content = readMultipleCharacters(reader, reader.read());
            System.out.println(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readMultipleCharacters(FileReader reader, int length) throws IOException {
        char[] buffer = new char[length];
        int charactersRead = reader.read(buffer, 0, length);
        if (charactersRead != -1) {
            return new String(buffer, 0, charactersRead);
        } else {
            return "";
        }
    }

    public static FileIO getInstance(){
        if(instance == null){
            instance = new FileIO();
        }
        return instance;
    }
}
