package ca.dreamteam.recipebook.Models;

import java.util.ArrayList;

import ca.dreamteam.recipebook.Helpers.JSONEngine;

import android.accounts.Account;

public class User {
	public ArrayList<Ingredient> ingredientsInPantry;
	public Account userProfile;
	public JSONEngine searchEngine;
	
	public void addIngredientToPantry(Ingredient i)
	{
		//TODO
	}
	
	public void removeIngredientFromPantry(Ingredient i)
	{
		//TODO
	}
}
