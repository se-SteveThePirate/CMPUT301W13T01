package ca.dreamteam.newrecipebook.Models;

import java.io.Serializable;

public class Ingredient implements Serializable
{
	private static final long serialVersionUID = -5978060205944130010L;
	private long id;
    private String name;
   
    
    public void setIngredient(String ingredient){
        this.name = ingredient;
    }
    
    public String getName(){
        return name;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    @Override
    public String toString(){
        return name;
    }
    
    public Ingredient(String Name)
    {
    	this.name = Name;
    }
}
