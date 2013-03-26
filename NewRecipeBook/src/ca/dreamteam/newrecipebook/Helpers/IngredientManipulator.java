package ca.dreamteam.newrecipebook.Helpers;

import java.util.List;

import ca.dreamteam.newrecipebook.Testcall;
import android.widget.ArrayAdapter;
import ca.dreamteam.newrecipebook.*;
import ca.dreamteam.newrecipebook.Models.Ingredient;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;


public class IngredientManipulator extends Activity
{
  
    
    IngredientSQLite datasource = new IngredientSQLite(null);
  
        
    public void addNewIngredient(){
        @SuppressWarnings("unchecked")
       // ArrayAdapter<Ingredient> adapter = (ArrayAdapter<Ingredient>) getListAdapter();
        Ingredient ingredient = null;
        
        EditText editText = (EditText) findViewById(R.id.editText1);
        String ingredients = editText.getText().toString();
        ingredient = datasource.createIngredient(ingredients);
       // adapter.add(ingredient);
    }
    
   

}

