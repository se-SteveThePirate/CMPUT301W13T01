package ca.dreamteam.recipebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewPicture extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_picture);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_picture, menu);
        return true;
    }
}
