package ca.dreamteam.recipebook.Models;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Recipe {
	public Integer id;
	public String name;
	public String author;
	public ArrayList<Bitmap> photos;
	public ArrayList<Ingredient> ingredients;
	public String instructions;
	public boolean starred;
	
	public void uploadPhoto(Bitmap newPhoto)
	{
		//TODO
	}
	
	public void share(String toEmail)
	{
		//TODO
	}
}



