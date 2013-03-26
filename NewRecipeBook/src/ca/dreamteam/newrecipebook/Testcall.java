package ca.dreamteam.newrecipebook;

import ca.dreamteam.newrecipebook.Helpers.IngredientManipulator;
import ca.dreamteam.newrecipebook.Helpers.IngredientSQLite;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class Testcall extends Activity {
    
    private IngredientSQLite datasource;
    private IngredientManipulator actor;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcall);
       
        ListView ingredientListView = (ListView)findViewById(R.id.pantryList);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_testcall, menu);
        return true;
    }
    
    public void addClicked(View view){
       
      }
/*    
    @Override
    public void onResume() {
      datasource.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }
    */
}
