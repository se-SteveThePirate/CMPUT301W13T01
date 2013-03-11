package ca.dreamteam.recipebook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

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
    
    public void takeAPic(View view) {
    	Intent intent = new Intent(this, NewPicture.class);
    	startActivity(intent);
    }
    
    //By process of elimination, I'm assuming that the ambiguous Load button 
    //is supposed to go to the recipe menu. Can anyone confirm this?  
    public void load(View view) {
    	Intent intent = new Intent(this, RecipeMenu.class);
    	startActivity(intent);
    }
    
    public void openPantry(View view) {
    	Intent intent = new Intent(this, PantryActivity.class);
    	startActivity(intent);
    }
    
    //Borrowed from http://stackoverflow.com/questions/3226495/android-exit-application-code
    public void exitApp(View view) {
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
    }
    
    
}
