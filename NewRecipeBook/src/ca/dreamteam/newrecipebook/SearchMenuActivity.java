package ca.dreamteam.newrecipebook;

import android.app.Activity;
import android.os.Bundle;

/**
 * Runs search menu
 * 
 * @version RecipeBook Project 4
 * @author Connor Bilec, David James, Steve Eckert and Maciej Ogrocki
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
}
