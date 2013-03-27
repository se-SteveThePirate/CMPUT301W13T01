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
<<<<<<< HEAD
	public boolean starred;
=======
	public ArrayList<String> tags;
	public Boolean globalEdit;
	public ArrayList<String> comments;
	public Boolean favorited;
	
>>>>>>> 6117ed43793cf1d1ad453f7f0484c5a575944846
	
	public void uploadPhoto(Bitmap newPhoto)
	{
		//TODO
	}
	
	public void share(String toEmail)
	{
		//TODO
	}
}



