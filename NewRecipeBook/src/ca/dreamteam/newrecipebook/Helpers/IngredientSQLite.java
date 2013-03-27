package ca.dreamteam.newrecipebook.Helpers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class IngredientSQLite
{
    private SQLiteDatabase database;
    private IngredientSqlTable dbHelper;
    private String[] allColumns = { IngredientSqlTable.COLUMN_ID,
        IngredientSqlTable.COLUMN_INGREDIENT };
    
    public IngredientSQLite(Context context) {
        dbHelper = new IngredientSqlTable(context);
        
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
        values.put(IngredientSqlTable.COLUMN_INGREDIENT, ingredient);
        long insertId = database.insert(IngredientSqlTable.TABLE_INGREDIENTS, null, values);
        Cursor cursor = database.query(IngredientSqlTable.TABLE_INGREDIENTS,
                allColumns, IngredientSqlTable.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Ingredient newIngredient = cursorToIngredient(cursor);
        cursor.close();
        return newIngredient;
    }
    
    public void deleteIngredient(Ingredient ingredient) {
        long id = ingredient.getId();
        System.out.println("Ingredient deleted with id: " + id);
        database.delete(IngredientSqlTable.TABLE_INGREDIENTS, IngredientSqlTable.COLUMN_ID
            + " = " + id, null);
      }
    
    public List<Ingredient> getAllIngredients(){
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        Cursor cursor = database.query(IngredientSqlTable.TABLE_INGREDIENTS,
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
}




