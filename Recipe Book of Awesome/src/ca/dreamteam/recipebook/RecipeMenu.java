package ca.dreamteam.recipebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RecipeMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recipe_menu, menu);
        return true;
    }
}
