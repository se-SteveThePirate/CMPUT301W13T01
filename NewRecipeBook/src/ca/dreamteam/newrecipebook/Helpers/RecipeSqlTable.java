package ca.dreamteam.newrecipebook.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RecipeSqlTable extends SQLiteOpenHelper
{
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_INGREDIENTS = "ingredients";
    public static final String COLUMN_INSTRUCTIONS = "instructions";
    public static final String COLUMN_TAGS = "tags";
    public static final String COLUMN_COMMENTS = "comments";
    public static final String COLUMN_FAVORITED = "favorited";

    private static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_RECIPES + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME + " text not null, " + COLUMN_NAME + " text not null, " + COLUMN_AUTHOR + " text not null, " + COLUMN_INGREDIENTS + " text not null, " + COLUMN_INSTRUCTIONS + " text not null, " + COLUMN_TAGS + " text not null, " + COLUMN_COMMENTS + " text not null, " + COLUMN_FAVORITED + " text not null);";

    public RecipeSqlTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    } 

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RecipeSqlTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }
}