package Helpers;


import java.util.List;
import java.util.Random;

import ca.dreamteam.recipebook.R;

import Models.Ingredient;
import Pantry.Pantry;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.*;

public class DatabaseManager extends ListActivity {
  private Pantry datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pantry);
    
    datasource = new Pantry();
    datasource.open();

    List<Ingredient> values = datasource.getAllIngredients();

    // Use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
    @SuppressWarnings("unchecked")
    ArrayAdapter<Ingredient> adapter = (ArrayAdapter<Ingredient>) getListAdapter();
    Ingredient ingredient = null;
    switch (view.getId()) {
    case R.id.add:
        EditText editText = (EditText) findViewById(R.id.editText1);
      String ingredient1 = editText.getText().toString();
     // int nextInt = new Random().nextInt(3);
      // Save the new comment to the database
      ingredient = datasource.createIngredient(ingredient1);
      adapter.add(ingredient);
      break;
    case R.id.delete:
      if (getListAdapter().getCount() > 0) {
        ingredient = (Ingredient) getListAdapter().getItem(0);
        datasource.deleteIngredient(ingredient);
        adapter.remove(ingredient);
      }
      break;
    }
    adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    datasource.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    datasource.close();
    super.onPause();
  }

} 
