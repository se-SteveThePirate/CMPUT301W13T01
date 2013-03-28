package ca.dreamteam.newrecipebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.dreamteam.newrecipebook.Helpers.IngredientDatabaseHelper;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class ViewIngredientActivity extends Activity {
	private IngredientDatabaseHelper db = new IngredientDatabaseHelper(this);
	private Ingredient ingredient = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_view);
 
        ingredient = (Ingredient)getIntent().getSerializableExtra("ingredient");
        
        if (ingredient == null)
        {
			Button buttonView = (Button)findViewById(R.id.ingredientAdd_multiPurposeButton);
			buttonView.setText("Add to Pantry");	
			buttonView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					EditText iName = (EditText) findViewById(R.id.ingredientAdd_nameEdit);
					
					if(!iName.getText().toString().equals(""))
					{
						IngredientDatabaseHelper datasource = new IngredientDatabaseHelper(getApplicationContext());
						datasource.createIngredient(iName.getText().toString());
					
						finish();
					}
				}
			});
		}
        else {
        	this.ingredient = (Ingredient)getIntent().getSerializableExtra("ingredient");
			((EditText)findViewById(R.id.ingredientAdd_nameEdit)).setText(this.ingredient.getName());
		}
    }
	
	@Override
	public void onResume()
	{
		db.open();
		super.onResume();
	}
	
	@Override
	public void onPause()
	{
		db.close();
		super.onPause();
	}
	
	public void deleteIngredient(View view)
	{
		db.deleteIngredient(this.ingredient);
		super.finish();
	}
	
	public void saveIngredient(View view)
	{
		this.ingredient.setIngredient(((EditText)findViewById(R.id.ingredientAdd_nameEdit)).getText().toString());
		db.updateIngredient(this.ingredient);
		super.finish();
	}
}
