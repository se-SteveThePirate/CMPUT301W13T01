package ca.dreamteam.recipebook;

import java.util.ArrayList;
import java.util.List;

import Helpers.MySQLiteHelper;
import Models.Ingredient;
import android.app.Activity;
import android.R.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import ca.dreamteam.recipebook.R;

public class PantryActivity extends Activity{

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
      MySQLiteHelper.COLUMN_COMMENT };

  public void IngredientsDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }
  
  @Override
  //Creates the Activity and several variables
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_pantry);
      IngredientsDataSource(getBaseContext());
      open();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.activity_pantry, menu);
      return true;
  }
  
  //Primary job is to get the inputted string for the ingredient name and call the createIngredient method
  public void addIngredientToDB(View view) {
	  	EditText ingredientET = (EditText) findViewById(R.id.ingredientName);
	  	String ingredient = ingredientET.getText().toString();
	  	
	  	createIngredient(ingredient);
	  
  }
  
  //I really don't know what this cursor thing is supposed to do. Anyone have any guidance?? -Steve
  public Ingredient createIngredient(String ingredient) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_COMMENT, ingredient);
    long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
        values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Ingredient newIngredient = cursorToIngredient(cursor);
    cursor.close();
    return newIngredient;
  }

  public void deleteIngredient(Ingredient ingredient) {
    String name = ingredient.getName();
    System.out.println("The following ingredient was deleted: " + name);
    database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
        + " = " + name, null);
  }

  public List<Ingredient> getAllIngredients() {
    List<Ingredient> ingredients = new ArrayList<Ingredient>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Ingredient ingredient = cursorToIngredient(cursor);
      ingredients.add(ingredient);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return ingredients;
  }

  private Ingredient cursorToIngredient(Cursor cursor) {
	Ingredient ingredient = new Ingredient();
	ingredient.setAmount(cursor.getInt(0));
	ingredient.setName(cursor.getString(1));
    return ingredient;
  }
} 