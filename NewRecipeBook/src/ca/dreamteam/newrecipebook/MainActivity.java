package ca.dreamteam.newrecipebook;

import ca.dreamteam.newrecipebook.R;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Main activity opens the menu and opens the pantry view
 * 
 * @version RecipeBook Project 4
 * @author Connor Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */

public class MainActivity extends Activity {
	@Override
/**
 * When created generates main information
 * 
 * @param savedInstanceState
 */
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/**
 * Loads menu
 * 
 * @param menu
 * @return ture
 */
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
 /**
* this method opens up/creates a recipe menu intent
* 
* @param view
*/
    
    public void openRecipeView(View view) {
    	Intent intent = new Intent(this, RecipeMenuActivity.class);
    	startActivity(intent);
    }
    
/**
* This method opens up/creates your pantry to store your ingredients
* @param view
*/
    
    public void openPantryView(View view) {
    	Intent intent = new Intent(this, PantryActivity.class);
    	startActivity(intent);
    }
}
