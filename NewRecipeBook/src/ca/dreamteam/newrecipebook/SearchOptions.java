package ca.dreamteam.newrecipebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
/**
 * Searches the data by title author tags and ingredients or any combination of them. Uses check boxes to
 * determine what to search by.
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */

public class SearchOptions extends Activity {
	/**
	 * @var titleBox: If checked will search by names of recipes.
	 * @var authorBox: If checked will search by users
	 * @var tagBox: If checked will search by tags placed on recipes by users
	 * @var ingredientBox: If checked will search by ingredient in the recipe. 
	 */
	private CheckBox titleBox;
	private CheckBox authorBox;
	private CheckBox tagBox;
	private CheckBox ingredientsBox;

	/**
	 * As the file is created it checks which box's are check and updates the variables accordingly 
	 * 
	 * @param savedInstanceState
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);
        
        titleBox = (CheckBox) findViewById(R.id.searchNameBox);
        authorBox = (CheckBox) findViewById(R.id.searchAuthorBox);
        tagBox = (CheckBox) findViewById(R.id.searchTagBox);
        ingredientsBox = (CheckBox) findViewById(R.id.searchIngredientsBox);
        
    }

    /**
     * returns true
     * 
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_search_options, menu);
        return true;
    }
    /** 
     * Checks which box's are checked and then searches for information accordingly. 
     * Reorders list with those files which are more relevant. 
     * 
     * @param v
     */
    public void search(View v) {
    	if (titleBox.isChecked()) {
    		//code for searching recipe titles
    	}
    	if (authorBox.isChecked()) {
    		//code for searching recipe authors
    	}
    	if (tagBox.isChecked()) {
    		//code for searching recipe tags
    	}
    	if (ingredientsBox.isChecked()) {
    		//code for searching recipe ingredients
    	}
    }
}
