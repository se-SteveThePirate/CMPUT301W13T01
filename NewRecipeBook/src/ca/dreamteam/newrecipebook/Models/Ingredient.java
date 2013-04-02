package ca.dreamteam.newrecipebook.Models;

import java.io.Serializable;

/**
 * This is the object for all out ingrediants that will be added to the list and database for food
 * the user inputs as having in his panty
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */
public class Ingredient implements Serializable
{
	/**
	 * @var serialVersionUID the id number 
	 * @var the id of the ingredient 
	 * @var the name of the ingredient 
	 * 
	 */
	private static final long serialVersionUID = -5978060205944130010L;
	private long id;
    private String name;
   
    /**
     * sets the name for the ingredient
     * 
     * @param ingredient
     */
    public void setIngredient(String ingredient){
        this.name = ingredient;
    }
    
    /**
     * returns the ingredient name
     * 
     * @return
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets the id number in the sql database
     * 
     * @param id
     */
    public void setId(long id){
        this.id = id;
    }
    
    /**
     * returns the SQL database id number
     * 
     * @return
     */
    public long getId(){
        return id;
    }
    
    /**
     * returns proper string format for individual ingredients
     * 
     * @return
     */
    @Override
    public String toString(){
        return name;    
    }
    
    /**
     * sets the name for the ingredient
     * 
     * @param Name
     */
    public Ingredient(String Name)
    {
    	this.name = Name;
    }
}
