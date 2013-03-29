package ca.dreamteam.newrecipebook;

import java.util.ArrayList;

import ca.dreamteam.newrecipebook.Models.Recipe;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

@TargetApi(11)
public class CreateRecipeActivity extends ListActivity {
	
	public Recipe newRecipe;
    private ArrayList<String> tempIngredientList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        adapter=new ArrayAdapter<String>(this,R.id.ingredientList,
                tempIngredientList);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_recipe, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void addIngredient(View view) {
    	//read input from Ingredient field and make it into a string.
    	EditText ingredientNameET = (EditText)findViewById(R.id.inputIngredients);
    	String ingredientName = ingredientNameET.getText().toString();
    	ingredientNameET.setText("");
    	
    	newRecipe.ingredients.add(ingredientName); 
    	
    	adapter.notifyDataSetChanged();
    }
    
    public void newRecipeSubmit(View view) {
    	
    	EditText recipeNameET = (EditText)findViewById(R.id.recipeName);
    	EditText authorNameET = (EditText)findViewById(R.id.recipeAuthor);
    	EditText recipeInstructionsET = (EditText)findViewById(R.id.recipeInstructions);

    	String recipeName = recipeNameET.getText().toString();
    	String authorName = authorNameET.getText().toString();
    	String recipeInstructions = recipeInstructionsET.getText().toString();

    	newRecipe.setName(recipeName);
    	newRecipe.setAuthor(authorName);
    	newRecipe.addInstructions(recipeInstructions);
    	
    	Intent submitIntent = new Intent();
    	startActivity(submitIntent);
    	
    	
    }
    
    
    
    
    
    

}