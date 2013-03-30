package ca.dreamteam.newrecipebook.Models;

import java.io.Serializable;

public class Ingredient implements Serializable
{
	private static final long serialVersionUID = -5978060205944130010L;
	private long id;
    private String name;
   
    //sets the name for the ingredient
    public void setIngredient(String ingredient){
        this.name = ingredient;
    }
    
    //returns the ingredient name
    public String getName(){
        return name;
    }
    
    //Sets the id number in the sql database
    public void setId(long id){
        this.id = id;
    }
    
    //returns the SQL database id number
    public long getId(){
        return id;
    }
    
    //returns proper string format for individual ingredients
    @Override
    public String toString(){
        return name;    
    }
    
    //sets the name for the ingredient
    public Ingredient(String Name)
    {
    	this.name = Name;
    }
}
