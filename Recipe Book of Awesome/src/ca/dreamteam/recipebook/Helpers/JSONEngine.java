//Bored.
package ca.dreamteam.recipebook.Helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


import android.content.Context;

import ca.dreamteam.recipebook.Models.Ingredient;
import ca.dreamteam.recipebook.Models.Recipe;

import com.google.gson.Gson;

public class JSONEngine {
	public ArrayList<Recipe> recipeCache;
	
	//This code is shamelessly taken from the ElastiSearch Client demo, as covered in the lab, and is available here:
	// https://github.com/rayzhangcl/ESDemo/blob/master/ESDemo/src/ca/ualberta/cs/CMPUT301/chenlei/ESClient.java
	private HttpClient httpClient = new DefaultHttpClient();
	//private Gson gson = new Gson();
	
	public String getEntityContent(HttpResponse response) throws IllegalStateException, IOException{
		BufferedReader bReader = new BufferedReader(
									new InputStreamReader(response.getEntity().getContent()));
		String output;
		String json = "";
		while ((output = bReader.readLine()) != null){
			json += output;
		}
		return json;
	}
	//End referenced code.
	
	public JSONEngine(Context context)
	{
		File dir = context.getFilesDir();
		File[] cachedFiles = dir.listFiles();
	}
	
	public ArrayList<Recipe> searchByKeywords(String keywords) 
	{
		return new ArrayList<Recipe>();
	}
	
	public ArrayList<Recipe> searchByPantry(ArrayList<Ingredient> ingredients) 
	{
		//TODO
		return new ArrayList<Recipe>();
	}
	
}
