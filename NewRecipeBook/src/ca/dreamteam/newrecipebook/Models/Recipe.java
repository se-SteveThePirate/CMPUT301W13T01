package ca.dreamteam.newrecipebook.Models;

import java.io.Serializable;
import java.util.ArrayList;

import ca.dreamteam.newrecipebook.Models.Ingredient;

import android.content.Intent;
import android.graphics.Bitmap;

public class Recipe implements Serializable {
    public Integer id;
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
	
	@Override
	public String toString()
	{
		return "<b>This needs to be formatted :)</b>";
	}
}
