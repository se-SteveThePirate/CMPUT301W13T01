package ca.dreamteam.newrecipebook;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

public class RecipeViewActivity extends Activity {
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_view);
		
        
    	Cursor c = getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
    	c.moveToFirst();
    	String nameString = c.getString(c.getColumnIndex(ContactsContract.Profile.DISPLAY_NAME));
    	
    	TextView tvTextView = (TextView)findViewById(R.id.mainActivity_TitleTextView);
    	tvTextView.setText("By: " + nameString);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_recipe_view, menu);
		return true;
	}

}
