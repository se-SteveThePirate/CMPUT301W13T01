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
    //These statements create instances of the classes who's methods we use
    //in this class

    private SQLiteDatabase database;
    private IngredientSqlHelper dbHelper;
    private String[] allColumns = { IngredientSqlHelper.COLUMN_ID,
            IngredientSqlHelper.COLUMN_INGREDIENT };

    //creates a usable context for IngredientSqlHelper
    public IngredientDatabaseHelper(Context context) {
        dbHelper = new IngredientSqlHelper(context);

    }

    //Opens the database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //Closes the database
    public void close() {
        dbHelper.close();
    }

    //Creates a cursor to move through all elements in the database
    private Ingredient cursorToIngredient(Cursor cursor) {
        Ingredient ingredient = new Ingredient(cursor.getString(1));
        ingredient.setId(cursor.getLong(0));
        return ingredient;
    }

    //Creates ingredients and inserts them into the database
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

    //Deletes selected ingredient from the database
    public void deleteIngredient(Ingredient ingredient) {
        long id = ingredient.getId();
        System.out.println("Ingredient deleted with id: " + id);
        database.delete(IngredientSqlHelper.TABLE_INGREDIENTS, IngredientSqlHelper.COLUMN_ID
                + " = " + id, null);
    }

    //Returns all of the ingredients in the database
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

    //Updates ingredients that have already been inserted or created in the SQL
    //database
    public void updateIngredient(Ingredient ingredient)
    {
        long id = ingredient.getId();
        ContentValues cv = new ContentValues();
        cv.put(IngredientSqlHelper.COLUMN_INGREDIENT, ingredient.getName());
        database.update(IngredientSqlHelper.TABLE_INGREDIENTS, cv, 
                IngredientSqlHelper.COLUMN_ID + "=" + id, null);
    }
}




