package ca.dreamteam.newrecipebook.Helpers;

import java.util.ArrayList;
import java.util.List;
import ca.dreamteam.newrecipebook.Models.IngredientSqlTable;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
}
