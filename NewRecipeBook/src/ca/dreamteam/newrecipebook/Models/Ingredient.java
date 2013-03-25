package ca.dreamteam.newrecipebook.Models;


public class Ingredient
{
    private long id;
    private String ingredient;
    
    public void setIngredient(String ingredient){
        this.ingredient = ingredient;
    }
    
    public String getIngredient(){
        return ingredient;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    @Override
    public String toString(){
        return ingredient;
    }
}
