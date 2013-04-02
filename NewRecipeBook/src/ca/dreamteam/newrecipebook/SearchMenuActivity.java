package ca.dreamteam.newrecipebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Runs search menu
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */

public class SearchMenuActivity extends Activity {
	@Override

/**
 * creates search menu
 *  
 * @param savedInstanceState
 */
	
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);  
    }
	
	public void keywordSearch(View v) {
		Intent intent = new Intent(this, SearchOptions.class);
		startActivity(intent);
	}
	
	public void pantrySearch(View v) {
		//TODO
		
	}
}
