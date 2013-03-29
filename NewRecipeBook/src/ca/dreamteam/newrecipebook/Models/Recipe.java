package ca.dreamteam.newrecipebook.Models;

import java.io.Serializable;
import java.util.ArrayList;

import ca.dreamteam.newrecipebook.Models.Ingredient;

import android.content.Intent;
import android.graphics.Bitmap;

public class Recipe implements Serializable {
    
    
    public long id;
    public String name;
    public String author;
    public ArrayList<Bitmap> photos;
    public ArrayList<Ingredient> ingredients;
    public String instructions;
    public ArrayList<String> tags;
    public Boolean globalEdit;
    public ArrayList<String> comments;
    public Boolean favorited;

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
    
    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }
    
    public void setIngredients (ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
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
    	return "";
    }
    
    public String toEmailString()
    {
        return "<b>This needs to be formatted :)</b>";
    }
    
    public void addIngredient(Ingredient ingredient) {
    	ingredients.add(ingredient); 	
    }
    
    public void addInstructions(String instructions) {
    	this.instructions = instructions;
    }
}
