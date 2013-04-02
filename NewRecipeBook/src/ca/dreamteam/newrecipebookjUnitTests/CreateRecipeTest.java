package ca.dreamteam.newrecipebookjUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import ca.dreamteam.newrecipebook.CreateRecipeActivity;
import ca.dreamteam.newrecipebook.Models.Ingredient;
import ca.dreamteam.newrecipebook.Models.Recipe;

public class CreateRecipeTest {
	
	String testAuthor = "Test Author";
	String testName = "Test Name";
	ArrayList<String> testIngredients = new ArrayList<String>();
	String testTags = "Test Tags";
	String testInstructions = "Test Instructions";
	
	
	public CreateRecipeTest() {}
	
	public void testRecipeCreation() {
		try {
			Recipe testRecipe = new Recipe();
			testRecipe.setAuthor("Test Author");
			testRecipe.setName("Test Name");
			testRecipe.instructions = testInstructions;
		}
		catch (Exception e) {
			fail("Someone screwed up");
		}
		
	}
	
	public void modifyRecipe() {
		try {
			Recipe testRecipe = new Recipe();
			testRecipe.setAuthor("Test Author");
			testRecipe.setName("Test Name");
			testRecipe.instructions = testInstructions;
			testRecipe.ingredients = testIngredients;
			testRecipe.setAuthor("New Author name");
			testRecipe.ingredients.add("New ingredient");
			testRecipe.setName("new name");
		}
		catch (Exception e) {
			fail("Someone screwed up");
		}
		
	}
	

}
