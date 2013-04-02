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

/**
 * Checks if there is no file and if there isn't it will make one. Also will read from the file
 * and serialize it. 
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */

public class RecipeSerialization
{
	/**
	 * @var singletonInstance is a RecipeSeilaztion Default set to null
	 * @var C is a context that is used a temp context 
	 */
    private static RecipeSerialization singletonInstance = null;
    private static Context c;
    
    protected RecipeSerialization()
    {
        //Prevent Instantiation
    }
    /**
     * Checks if there is a singletionIstance if there isn't it will make a new recipeserilzation known as
     * singletonInstance
     * 
     * @param c
     * @return
     */
    public static RecipeSerialization getInstance(Context c){
        RecipeSerialization.c = c;
        if (singletonInstance == null)
            singletonInstance = new RecipeSerialization();
        
        return singletonInstance;
    }
    /**
     * Checks if there is a file in the directory exists and will create a new file
     * else it will delte f and create a new file then write the object recipe to the file. 
     * and finally closes the file. 
     * 
     * @param recipe
     */
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
    /**
     * Trys to connect to the stream and read the recipe file from it if it can it will do so
     * and close the stream if it fails it will output and error.
     * 
     * @param fileId
     * @return
     */
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
/**
 * deletes the file. I would be careful with this funtion if I were you. 
 * 
 * @param fileId
 */
    public void deleteFile(long fileId){
        File file = new File(fileId + ".ser");
        file.delete();
    }
}
