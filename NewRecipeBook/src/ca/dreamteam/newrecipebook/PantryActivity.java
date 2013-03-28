package ca.dreamteam.newrecipebook;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import ca.dreamteam.newrecipebook.Helpers.IngredientDatabaseHelper;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class PantryActivity extends ListActivity {
    private IngredientDatabaseHelper datasource;
    private ArrayAdapter<Ingredient> adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        datasource = new IngredientDatabaseHelper(this);
        datasource.open();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        
        getListView().setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View view, int position, long id )
        	{
        		Ingredient i = (Ingredient)adapter.getItem(position);
        		
        		Intent viewIntent = new Intent(getApplicationContext(), ViewIngredientActivity.class);
        		viewIntent.putExtra("ingredient", i);
        		startActivity(viewIntent);
        	}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pantry, menu);
        return true;
    }
    
    public void addClicked(View view){
        Intent i = new Intent(this, ViewIngredientActivity.class);
    	startActivity(i);
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      datasource.open();
      //adapter
      List<Ingredient> values = datasource.getAllIngredients();
      
      // Use the SimpleCursorAdapter to show the
      // elements in a ListView
      adapter = new ArrayAdapter<Ingredient>(this,
          android.R.layout.simple_list_item_1, values);
      setListAdapter(adapter);
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }
}
