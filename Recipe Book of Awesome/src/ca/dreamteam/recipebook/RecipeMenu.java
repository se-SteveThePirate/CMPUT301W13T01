package ca.dreamteam.recipebook;

import ca.dreamteam.recipebook.Helpers.JSONEngine;
import ca.dreamteam.recipebook.Models.Recipe;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecipeMenu extends Activity {
	
	private ListView recipeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_menu);
        recipeList = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<Recipe> recipeAdapter = new ArrayAdapter<Recipe>
        	(this,android.R.layout.simple_list_item_1,JSONEngine.recipeCache);
        recipeList.setAdapter(recipeAdapter);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recipe_menu, menu);
        return true;
    }
}
