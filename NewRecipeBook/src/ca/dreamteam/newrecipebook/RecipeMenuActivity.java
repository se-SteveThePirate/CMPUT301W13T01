package ca.dreamteam.newrecipebook;

import ca.dreamteam.newrecipebook.Models.Recipe;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeMenuActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_menu);
        
        ListView lvListView = getListView();
        TextView emptyTextView = (TextView)findViewById(R.id.recipeActivity_emptyListViewText);
        lvListView.setEmptyView(emptyTextView);
    }

    @Override
    public void onResume()
    {
        TextView emptyTextView = (TextView)findViewById(R.id.recipeActivity_emptyListViewText);
        if (emptyTextView.isShown())
        {
        	Button button = (Button)findViewById(R.id.recipeListActivity_downloadButton);
        	button.setText("Download Recipes");
        }
        super.onResume();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recipe_menu, menu);
        return true;
    }
    
    public void goSearchMenu(View view)
    {
    	Intent intent = new Intent(this, SearchMenuActivity.class);
    	startActivity(intent);
    }
    
    //Todo: Have a listItemClick event use this method to show the real recipes.
    public void goAddRecipe(View view)
    {
    	Intent intent = new Intent(this, CreateRecipeActivity.class);
    	startActivity(intent);
    }
}
