package ca.dreamteam.newrecipebook.Helpers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class IngredientDatabaseHelper
{
    private SQLiteDatabase database;
    private IngredientSqlHelper dbHelper;
    private String[] allColumns = { IngredientSqlHelper.COLUMN_ID,
        IngredientSqlHelper.COLUMN_INGREDIENT };
    
    public IngredientDatabaseHelper(Context context) {
        dbHelper = new IngredientSqlHelper(context);
        
      }
    
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
      }

    public void close() {
        dbHelper.close();
      }
    
    private Ingredient cursorToIngredient(Cursor cursor) {
        Ingredient ingredient = new Ingredient(cursor.getString(1));
        ingredient.setId(cursor.getLong(0));
        return ingredient;
      }
    
    public Ingredient createIngredient(String ingredient){
    	if(database == null){
    		this.open();
    	}
    	ContentValues values = new ContentValues();
        values.put(IngredientSqlHelper.COLUMN_INGREDIENT, ingredient);
        long insertId = database.insert(IngredientSqlHelper.TABLE_INGREDIENTS, null, values);
        Cursor cursor = database.query(IngredientSqlHelper.TABLE_INGREDIENTS,
                allColumns, IngredientSqlHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Ingredient newIngredient = cursorToIngredient(cursor);
        cursor.close();
        return newIngredient;
    }
    
    public void deleteIngredient(Ingredient ingredient) {
        long id = ingredient.getId();
        System.out.println("Ingredient deleted with id: " + id);
        database.delete(IngredientSqlHelper.TABLE_INGREDIENTS, IngredientSqlHelper.COLUMN_ID + " = " + id, null);
      }
    
    public List<Ingredient> getAllIngredients(){
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        Cursor cursor = database.query(IngredientSqlHelper.TABLE_INGREDIENTS,
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
    
    public void updateIngredient(Ingredient ingredient)
    {
    	long id = ingredient.getId();
    	ContentValues cv = new ContentValues();
    	cv.put(IngredientSqlHelper.COLUMN_INGREDIENT, ingredient.getName());
    	database.update(IngredientSqlHelper.TABLE_INGREDIENTS, cv, 
    			IngredientSqlHelper.COLUMN_ID + "=" + id, null);
    }
}




