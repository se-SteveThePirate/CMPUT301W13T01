package ca.dreamteam.newrecipebook.Helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.dreamteam.newrecipebook.Models.Recipe;

//majority of this code has been modified from tutorials point website
//http://www.tutorialspoint.com/java/java_serialization.htm

public class RecipeSerialization
{

    public void makeFile(String name, String author, String instructions){

        //takes in the recipe information that was passed to it
        Recipe r = new Recipe();
        r.name = name;
        r.author = author;
        r.instructions = instructions;


        try{
            FileOutputStream fileOut =
                    new FileOutputStream(r.name + ".ser");
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);
            out.writeObject(r);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }

    }

    public Recipe readFile(String filename){
        Recipe r = null;
        try{
            FileInputStream fileIn =
                    new FileInputStream(filename + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            r = (Recipe) in.readObject();
            in.close();
            fileIn.close();

        }catch(IOException i)
        {
            i.printStackTrace();
        }catch(ClassNotFoundException c)
        {
            System.out.println(".Recipe class not found.");
            c.printStackTrace();
        }
        return r;
    }
}
