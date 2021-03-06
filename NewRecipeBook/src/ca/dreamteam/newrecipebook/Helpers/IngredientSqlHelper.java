package ca.dreamteam.newrecipebook.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class IngredientSqlHelper extends SQLiteOpenHelper
{
    /**
     * These variables are for the sql table that is created for storing ingredients
     */

    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_INGREDIENT = "ingredient";

    private static final String DATABASE_NAME = "ingredients.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * This statement formats and SQL query to get the actual SQL table
     */
    private static final String DATABASE_CREATE = "create table "
            + TABLE_INGREDIENTS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_INGREDIENT
            + " text not null);";

    /**
     * Gets the proper context of the table and database
     * 
     * @param context
     */
    public IngredientSqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method creates the database for ingredients
     * 
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    /**
     * This method updates the database if there are any changes
     * 
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(IngredientSqlHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
    }

}
