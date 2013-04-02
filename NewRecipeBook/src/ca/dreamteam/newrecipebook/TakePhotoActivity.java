package ca.dreamteam.newrecipebook;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * Used to take photos of recipes that can be using the camera and saves the image to the phone.
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */

public class TakePhotoActivity extends Activity {
/**
 * @var imageFileUrl: Where the images of the photo will be saved
 * @var CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE set too 100 and passed to startActivityForResult class
 */
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    Uri imageFileUri;
    
    @Override
    
    /**
     * creates the page and waits for the button to be pressed at which point the takeAPhoto class is called
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        
        ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v){
                takeAPhoto();
            }
        };
        button.setOnClickListener(listener);
    }

    @Override
    /**
     * creates option menu and returns true
     * 
     * @param menu
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_take_photo, menu);
        return true;
    }
    
 /**
  * When button is pressed creates a file that is to be filled with the picture that is going to be
  * taken. Then calls startActivityForResult
  */
    
    public void takeAPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
        File folderF = new File(folder);
        if (!folderF.exists()) {
            folderF.mkdir();
        }
        
        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
        File imageFile = new File(imageFilePath);
        imageFileUri = Uri.fromFile(imageFile);
        
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
    /**
     * takes photo when button pressed. 
     * 
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                            
        ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
        button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));    
    }
    /**
     * Resends the finsihed photo.
     * 
     * @param v
     */
    public void returnPhoto(View v) {
    	Intent intent = new Intent(this, CreateRecipeActivity.class);
    	intent.setData(imageFileUri);
    	finish();

    }
    /** 
     * turns off camera when cancel button is pressed.  
     */
    protected void onCancelActivity(){
        finish();
    }
    
}