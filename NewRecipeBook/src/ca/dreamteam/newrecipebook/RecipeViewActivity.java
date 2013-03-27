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

public class RecipeViewActivity extends Activity {
	private Recipe recipe;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.recipe = (Recipe) getIntent().getSerializableExtra("recipe");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_view);

		
    	//TODO For Maciej: Make sure to set EVERYTHING to uneditable when viewing. We could pass a bool around or something.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_recipe_view, menu);
		return true;
	}

	public void share(View view)
	{
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "A friend has recommended a recipe for you!");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(recipe.toString()));
		startActivity(Intent.createChooser(emailIntent, "Share a recipe..."));
	}	
}
