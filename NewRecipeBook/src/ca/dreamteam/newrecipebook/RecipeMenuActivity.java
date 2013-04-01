package ca.dreamteam.newrecipebook;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import ca.dreamteam.newrecipebook.Helpers.RecipeSQLite;
import ca.dreamteam.newrecipebook.Models.Recipe;

/**
 * Opens the database and allows the used to fetch the position of the item in the list from the 
 * database. Uses the the simplecursoradapter to show elements in a listview
 * 
 * @version RecipeBook Project 4
 * @author Connor Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */


public class RecipeMenuActivity extends ListActivity {
	/**
	 * @var datasource used to connect to the database.
	 * @var adapter the array list of the recipes  
	 */
    private RecipeSQLite datasource;
    private ArrayAdapter<Recipe> adapter;
    @Override
    /**
     * Links the database to the program and opens the data base. Allows the used to click on 
     * a item in the list and fetches the position in the database
     * 
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) { 
        datasource = new RecipeSQLite(this); //links up with the database in another class
        datasource.open();//opens said database

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_menu);

        //This allows you to click on an item in the list that is displayed
        //and fetchs its position in the database
        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View view, int position, long id )
            {
                Recipe r = (Recipe)adapter.getItem(position);

                Intent viewIntent = new Intent(getApplicationContext(), CreateRecipeActivity.class);
                viewIntent.putExtra("Recipe", r);
                startActivity(viewIntent);
            }
        });

        /*  ListView lvListView = getListView();
        TextView emptyTextView = (TextView)findViewById(R.id.recipeActivity_emptyListViewText);
        lvListView.setEmptyView(emptyTextView);
         */
    }
    /**
     * Opens the database when the page is returned too
     */
    @Override
    public void onResume()
    {
        /*   TextView emptyTextView = (TextView)findViewById(R.id.recipeActivity_emptyListViewText);
        if (emptyTextView.isShown())
        {
        	Button button = (Button)findViewById(R.id.recipeListActivity_downloadButton);
        	button.setText("Download Recipes");
        }
         */
        super.onResume();
        datasource.open();
        //adapter
        List<Recipe> values = datasource.getAllRecipes();

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter = new ArrayAdapter<Recipe>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }
/**
 * closes the database when page is left
 */
    public void onPause() {
        datasource.close();
        super.onPause();
    }
/**
 * starts the menu
 * 
 * @param menu
 * @return true
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recipe_menu, menu);
        return true;
    }
/**
 * Starts the search menu activity
 * 
 * @param view
 */
    public void goSearchMenu(View view)
    {
        Intent intent = new Intent(this, SearchMenuActivity.class);
        startActivity(intent);
    }

 /**
  * starts the createRecipeActivity class
  * 
  * @param view
  */
    public void goAddRecipe(View view)
    {
        Intent intent = new Intent(this, CreateRecipeActivity.class);
        startActivity(intent);
    }

/**
 * Deletes the data of something in the list by clicking on it
 *     
 * @param l
 * @param v
 * @param position
 * @param id
 */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Recipe recipeToDelete = (Recipe)getListView().getItemAtPosition(position);
		datasource.deleteRecipe(recipeToDelete);
		adapter.notifyDataSetChanged();
	}
<<<<<<< HEAD

=======
>>>>>>> 15aa7e72c8a57d8ecff45fa54d402a2d8a3738ef
}
