package ca.dreamteam.newrecipebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.dreamteam.newrecipebook.Helpers.IngredientSQLite;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class ViewIngredientActivity extends Activity {
	private Ingredient ingredient = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_view);
 
        ingredient = (Ingredient)getIntent().getSerializableExtra("ingredient");
        
        if (ingredient == null)
        {
			Button buttonView = (Button)findViewById(R.id.ingredientAdd_multiPurposeButton);
			buttonView.setText("Add New Ingredient");	
			buttonView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					EditText iName = (EditText) findViewById(R.id.ingredientAdd_nameEdit);
					
					if(!iName.getText().toString().equals(""))
					{
						IngredientSQLite datasource = new IngredientSQLite(getApplicationContext());
						datasource.createIngredient(iName.getText().toString());
					
						finish();
					}
				}
			});
		}
    }
	
	
}
