package ca.dreamteam.newrecipebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SearchOptions extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_options, menu);
        return true;
    }
}
