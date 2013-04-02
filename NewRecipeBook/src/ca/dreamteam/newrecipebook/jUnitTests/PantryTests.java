package ca.dreamteam.newrecipebook.jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import android.widget.ArrayAdapter;
import ca.dreamteam.newrecipebook.CreateRecipeActivity;
import ca.dreamteam.newrecipebook.PantryActivity;
import ca.dreamteam.newrecipebook.Helpers.IngredientDatabaseHelper;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class PantryTests extends PantryActivity {
	
    private IngredientDatabaseHelper testDatasource;
    private ArrayAdapter<Ingredient> testAdapter;
    
    public PantryTests() {
    	super();
    }
    
    public void databaseCreationTest() {
    	try {
	    	testDatasource = new IngredientDatabaseHelper(this);
	    	testDatasource.open();
	    	testDatasource.createIngredient("new ingredient");
    	}
    	catch (Exception e) {
			fail("Someone screwed up");
    	}
    }
    
    public void databaseViewTest() {
    	try {
	    	testDatasource = new IngredientDatabaseHelper(this);
	    	testDatasource.open();
	    	testDatasource.createIngredient("new ingredient 1");
	    	testDatasource.createIngredient("new ingredient 2");
	    	testDatasource.getAllIngredients();
    	}
    	catch (Exception e) {
			fail("Someone screwed up");
    	}
    }

}
