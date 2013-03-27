package ca.dreamteam.newrecipebook;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import ca.dreamteam.newrecipebook.Helpers.IngredientSQLite;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class PantryActivity extends ListActivity {
    private IngredientSQLite datasource;
    private ArrayAdapter<Ingredient> adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        datasource = new IngredientSQLite(this);
        datasource.open();
        
        List<Ingredient> values = datasource.getAllIngredients();
        
        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter = new ArrayAdapter<Ingredient>(this,
            android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
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
      datasource.open();
      adapter.notifyDataSetChanged();
      super.onResume();
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }
}
