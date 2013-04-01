package ca.dreamteam.newrecipebook;

import ca.dreamteam.newrecipebook.Models.Recipe;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Allows the user to view the recipe
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */

public class RecipeViewActivity extends Activity {
	/**
	 * @var recipe keeps track of the current recipe and serializes it
	 */
	
	private Recipe recipe;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	
/**
 * creates the view and serializes the recipe
 * 	
 * @param savedInstanceState
 */
	protected void onCreate(Bundle savedInstanceState) {
		this.recipe = (Recipe) getIntent().getSerializableExtra("recipe");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_view);

		
    	//TODO For Maciej: Make sure to set EVERYTHING to uneditable when viewing. We could pass a bool around or something.
	}
/**
 * Loads options menu
 * 
 * @param menu
 * @return true
 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_recipe_view, menu);
		return true;
	}
/**
 * Allows the user to share the recipe to other users using email
 * 
 * @param view
 */
	public void share(View view)
	{
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "A friend has recommended a recipe for you!");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(recipe.toString()));
		startActivity(Intent.createChooser(emailIntent, "Share a recipe..."));
	}	
}
