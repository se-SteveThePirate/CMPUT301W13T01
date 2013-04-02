package ca.dreamteam.newrecipebook.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

import ca.dreamteam.newrecipebook.Models.Recipe;

//majority of this code has been modified from tutorials point website
//http://www.tutorialspoint.com/java/java_serialization.htm

public class RecipeSerialization
{
    private static RecipeSerialization singletonInstance = null;
    private static Context c;
    
    protected RecipeSerialization()
    {
        //Prevent Instantiation
    }
    
    public static RecipeSerialization getInstance(Context c){
        RecipeSerialization.c = c;
        if (singletonInstance == null)
            singletonInstance = new RecipeSerialization();
        
        return singletonInstance;
    }
    
    public void makeFile(Recipe recipe){

        try{
            File f = new File(c.getFilesDir() + "/" + recipe.getId() + ".ser");
            if (!f.exists())
                f.createNewFile();
            else
            {
                f.delete();
                f.createNewFile();
            }
            FileOutputStream fileOut =
                    new FileOutputStream(c.getFilesDir() + "/" + recipe.getId() + ".ser");
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);
            out.writeObject(recipe);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }

    }

    public Recipe readFile(long fileId){
        Recipe r = null;
        try{
            FileInputStream fileIn =
                    new FileInputStream(c.getFilesDir() + "/" + fileId + ".ser");
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

    public void deleteFile(long fileId){
        File file = new File(fileId + ".ser");
        file.delete();
    }
}
