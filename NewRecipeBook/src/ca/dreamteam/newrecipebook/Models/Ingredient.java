package ca.dreamteam.newrecipebook.Models;


public class Ingredient
{
    private String id;
    private String name;
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
}
