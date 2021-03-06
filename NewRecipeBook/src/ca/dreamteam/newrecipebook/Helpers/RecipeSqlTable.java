package ca.dreamteam.newrecipebook.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
* Attempts to create the sql table and populate it. Will throw errors if it fails. 
* 
* @version RecipeBook Project 4
* @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
* @date Monday 01 April 2013
*/
public class RecipeSqlTable extends SQLiteOpenHelper
{
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";


    private static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_RECIPES
            + "(" + COLUMN_ID + " integer primary key, " + COLUMN_NAME
            + " text not null);";

    public RecipeSqlTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    } 

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RecipeSqlTable.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }
}