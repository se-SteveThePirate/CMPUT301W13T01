package ca.dreamteam.newrecipebook;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import ca.dreamteam.newrecipebook.Helpers.IngredientDatabaseHelper;
import ca.dreamteam.newrecipebook.Models.Ingredient;

/**
 * Opens the data base then allows the used to click on items and fetch its position in the database
 * also allows the user pull up and add ingredient activity finally also pulls information from the database
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */

public class PantryActivity extends ListActivity {
	/**
	 * @var datasource used to help connect to the database
	 * @var adapter the array list of the ingredients 
	 */
    private IngredientDatabaseHelper datasource;
    private ArrayAdapter<Ingredient> adapter;

    /**
     * When created connects to the database opens the database and then allows the used to click
     * on items on the data base and fetch its position in the database
     * 
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        datasource = new IngredientDatabaseHelper(this); //links up with the database made in another class
        datasource.open();//opens said database

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        //This allows you to click on an item in the list that is displayed
        //and fetchs its position in the database
        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View view, int position, long id )
            {
                Ingredient i = (Ingredient)adapter.getItem(position);

                Intent viewIntent = new Intent(getApplicationContext(), ViewIngredientActivity.class);
                viewIntent.putExtra("ingredient", i);
                startActivity(viewIntent);
            }
        });
    }
/**
 * creates the menu
 * 
 * @param menu
 * @return true
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pantry, menu);
        return true;
    }

    /**
     * this method is activated when you click the add button
     * it pulls up the add ingredient activity
     * 
     * @param view
     */
    
    public void addClicked(View view){
        Intent i = new Intent(this, ViewIngredientActivity.class);
        startActivity(i);
    }

    @Override
    /**
     *  Use the SimpleCursorAdapter to show the elements in a ListView
     */
    protected void onResume() {
        super.onResume();
        datasource.open();
        //adapter
        List<Ingredient> values = datasource.getAllIngredients();

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter = new ArrayAdapter<Ingredient>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    /**
     * Closes the data base when you leave the screen
     */
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
