package ca.dreamteam.newrecipebook.Helpers;


import android.widget.ArrayAdapter;
import ca.dreamteam.newrecipebook.*;
import ca.dreamteam.newrecipebook.Models.Ingredient;
import ca.dreamteam.newrecipebook.PantryActivity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;


public class IngredientManipulator extends ListActivity
{
    
    @SuppressWarnings("unchecked")
    ArrayAdapter<Ingredient> adapter = (ArrayAdapter<Ingredient>) getListAdapter();
    Ingredient ingredient = null;
    
    
    public void addNewIngredient(){
        /*
        EditText editText = (EditText) findViewById(R.id.editText1);
        */
        //need to add a text edit field in the UI to get this line to work. done
        //for the day, need to get to work. 
    }
    

}
