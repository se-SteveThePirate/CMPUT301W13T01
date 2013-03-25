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

public class MainActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
      
    public void openRecipeView(View view) {
    	Intent intent = new Intent(this, RecipeMenuActivity.class);
    	startActivity(intent);
    }
    
    
    public void openPantry(View view) {
    	Intent intent = new Intent(this, PantryActivity.class);
    	startActivity(intent);
    }
}
