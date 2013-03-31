package ca.dreamteam.newrecipebook;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import ca.dreamteam.newrecipebook.Helpers.IngredientDatabaseHelper;
import ca.dreamteam.newrecipebook.Models.Ingredient;

public class AddIngredientForRecipeActivity extends ListActivity{
	private Ingredient ingredient = null;
	private ArrayList<String> ingredientList = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	
	@Override	
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_view);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                ingredientList);
        setListAdapter(adapter);
 
        //ingredient = (Ingredient)getIntent().getSerializableExtra("ingredient");
        
        if (ingredient == null)
        {
			Button buttonView = (Button)findViewById(R.id.ingredientAdd_multiPurposeButton);
			buttonView.setText("Add to Recipe");	
			buttonView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					EditText iNameET = (EditText) findViewById(R.id.ingredientAdd_nameEdit);
					String ingredientName = iNameET.getText().toString();
					
					if(!ingredientName.equals(""))
					{
						ingredientList.add(ingredientName);
						iNameET.setText("");
						adapter.notifyDataSetChanged();
						/*Intent intent = new Intent();
						intent.putExtra("newingredient", new Ingredient(iName.getText().toString()));
						setResult(RESULT_OK, intent);
						finish();*/
					}
				}
			});
			
			buttonView = (Button)findViewById(R.id.ingredientDelete_multiPurposeButton);
			buttonView.setText("Done Adding");
			buttonView.setOnClickListener(new View.OnClickListener(){
			    
			    @Override
			    public void onClick(View v) {
			    	Intent intent = new Intent();
					intent.putStringArrayListExtra("newIngredients", ingredientList);
					setResult(RESULT_OK, intent);
					finish();
			    }
			});
        }
		
        else {
        	this.ingredient = (Ingredient)getIntent().getSerializableExtra("ingredient");
			((EditText)findViewById(R.id.ingredientAdd_nameEdit)).setText(this.ingredient.getName());
		}
    }
}
