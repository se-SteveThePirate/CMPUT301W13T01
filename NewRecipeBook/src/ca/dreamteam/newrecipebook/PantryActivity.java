package ca.dreamteam.newrecipebook;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import ca.dreamteam.newrecipebook.Helpers.IngredientDatabaseHelper;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class PantryActivity extends ListActivity {
    private IngredientDatabaseHelper datasource;
    private ArrayAdapter<Ingredient> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        datasource = new IngredientDatabaseHelper(this); //links up with the database made in another class
        datasource.open();//opens said database

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        //This allows you to click on an item in the list that is displayed
        //and fetchs its position in the database
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

    //this method is activated when you click the add button
    //it pulls up the add ingredient activity
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
