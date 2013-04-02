package ca.dreamteam.newrecipebook.Models;

import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.Bitmap;
/**
 * This is the object of the recipe object. Most of the classes and functions should be clear from the name. This 
 * should work as a file for the recipe's and be used to store data by the user about the recipes they have written
 * and downloaded on the app. 
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */
public class Recipe implements Serializable {

    private static final long serialVersionUID = -3419759999296394802L;
    public long id;
    public String name;
    public String author;
    public String jsonString;
    public ArrayList<String> ingredients;
    public String instructions;
    public String tags;
    public ArrayList<String> comments;
    public Boolean favorited;
    public int favorite;


    public void uploadPhoto(Bitmap newPhoto)
    {
        //TODO
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }

    public void setIngredients (ArrayList<String> ingredients){
        this.ingredients = ingredients;
    }
    
    public void setJsonString(String jsonString){
        this.jsonString = jsonString;
    }
    
    public String getJsonString(){
        return jsonString;
    }

    public void setTags (String tags){
        this.tags = tags;
    }
    
    public String getTags(){
        return tags;
    }

    public void addIngredient(Ingredient ingredient)
    {
        this.ingredients.add(ingredient.getName());
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public String toEmailString()
    {
        return "<p>" + this.name + "</p>" + "<p>Author: " + this.author
                + "</p>" + "<p>Ingredients: " + this.ingredients + "</p>"
                + "<p>Instructions: " + this.instructions + "</p>";
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient); 	
    }

    public void addInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions(){
        return instructions;
    }
}
