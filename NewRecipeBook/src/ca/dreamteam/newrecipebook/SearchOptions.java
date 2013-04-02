package ca.dreamteam.newrecipebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class SearchOptions extends Activity {
	
	private CheckBox titleBox;
	private CheckBox authorBox;
	private CheckBox tagBox;
	private CheckBox ingredientsBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);
        
        titleBox = (CheckBox) findViewById(R.id.searchNameBox);
        authorBox = (CheckBox) findViewById(R.id.searchAuthorBox);
        tagBox = (CheckBox) findViewById(R.id.searchTagBox);
        ingredientsBox = (CheckBox) findViewById(R.id.searchIngredientsBox);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_options, menu);
        return true;
    }
    
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
