package ca.dreamteam.newrecipebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ca.dreamteam.newrecipebook.Helpers.ImageConverter;
import ca.dreamteam.newrecipebook.Helpers.RecipeSQLite;
import ca.dreamteam.newrecipebook.Helpers.RecipeSerialization;
import ca.dreamteam.newrecipebook.Helpers.ElasticSearch.ESClient;
import ca.dreamteam.newrecipebook.Models.Recipe;

/**
 * Attempts to connect to the database nothing happens if failed then
 * Creates a file from the information currently in the text blocks and
 * then uploads them to the database
 * 
 * @version RecipeBook Project 4
 * @author Conner Bilec, David James, Steve Eckert and Maciej Ogrocki
 * @date Monday 01 April 2013
 */


@TargetApi(14)
public class CreateRecipeActivity extends Activity {
	
/**
 * @var recipeCache used to hold recipe information on client side
 * @var recipeSerilazion used to serialize information about client side
 * @var newRecipe Temp new recipe object null by default
 * @var tempIntgredientList a list of ingredients on the client side that is temp
 */
    private RecipeSQLite recipeCache = new RecipeSQLite(this);
    //private RecipeSerialization recipeSerial = RecipeSerialization.getInstance(this);
    private Recipe newRecipe = new Recipe();
    private ArrayList<String> tempIngredientList = new ArrayList<String>();
    private LinearLayout photoViewLayout;
    private TextView youScrewedUpTV;
    private String imageFilePath;
    private ImageView newImageView;
    private String jsonString;

/**
 * On creation waits for a button press at which point it will add the information in the text 
 * Fields and adds them to the database. Also uses serialization and will do nothing if the 
 * text Fields are blank. 
 * 
 * @param savedInstanceState
 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        photoViewLayout = (LinearLayout)findViewById(R.id.photoViewLayout);
        newRecipe.ingredients = new ArrayList<String>();
        //newRecipe.jsonString = jsonString;
        youScrewedUpTV = (TextView)findViewById(R.id.youScrewedUp);
        youScrewedUpTV.setVisibility(View.GONE);
          
        try {
            //Set Author's name.
            Cursor c = getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
            c.moveToFirst();
            String nameString = c.getString(c.getColumnIndex(ContactsContract.Profile.DISPLAY_NAME));

            TextView tvTextView = (TextView)findViewById(R.id.recipeAuthor);
            tvTextView.setText(nameString);

        } catch (Exception e) {
            //Meh.
        }
    }
    /**
     * When created adds menu
     * 
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_recipe, menu);
        return true;
    }

    /**
     * When items are selected get's the id and return 
     * 
     * @param item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Adds a ingredient 
     * 
     * @param view
     */
    public void addIngredient(View view) {
        Intent intent = new Intent(this, AddIngredientForRecipeActivity.class);
    	intent.putStringArrayListExtra("alreadyAddedIngredients", newRecipe.ingredients);
    	startActivityForResult(intent, 1);
    }
    /**
     * If requestCode is 1  and resultCode is RESULT_OK then tempIngerientList is filled with
     * all the ingredients 
     * 
     * @param requestCode true = 1 else false
     * @param resultCode true =  RESULT_OK else false
     * @param data Where the information for the ingredient's are held
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) //Used for getting Ingredient things
        {
            if (resultCode == RESULT_OK)
            {
                newRecipe.ingredients = (ArrayList<String>)data.getStringArrayListExtra("newIngredients");
            }
        }
        if (requestCode == 2) //Used for getting Picture things
        {
        	//Bitmap photo = Media.getBitmap(this.getContentResolver(), imageFileUri);
			Bitmap photo = BitmapFactory.decodeFile(imageFilePath);
			ImageConverter imgcon = new ImageConverter();
			jsonString = imgcon.getJsonString(photo);    //converts bitmap to a JsonString 
			newRecipe.setJsonString(jsonString); //stores JsonString in the new recipe
			/*ImageView imageView = (ImageView) findViewById(R.id.recipeGallery);
			imageView.setPadding(5, 0, 0, 0);
			imageView.setImageBitmap(photo);
			photoLayout.addView(imageView);*/
        	

        	
        	/*Bitmap newPhoto = BitmapFactory.decodeFile(data.getData().getPath());
        	if (newPhoto != null) {
        		newRecipe.photos.add(newPhoto);*/
        		newImageView = new ImageView(this);
        		newImageView.setPadding(5, 0, 0, 0);
        		newImageView.setImageBitmap(photo);
        		photoViewLayout.addView(newImageView);
        			
        	
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * saves the information on the screen to variables then
     * submits the data into the database and closes the date base
     * 
     * @param view
     */
    
    
    public void newRecipeSubmit(View view) {

        EditText recipeNameET = (EditText)findViewById(R.id.recipeName);
        EditText authorNameET = (EditText)findViewById(R.id.recipeAuthor);
        EditText recipeInstructionsET = (EditText)findViewById(R.id.recipeInstructions);
        

      //  RecipeSQLite datasource = new RecipeSQLite(getApplicationContext());
      //  datasource.createRecipe(newRecipe);

        String recipeName = recipeNameET.getText().toString();
        String authorName = authorNameET.getText().toString();
        String recipeInstructions = recipeInstructionsET.getText().toString();

        newRecipe.setName(recipeName);
        newRecipe.setAuthor(authorName);
        newRecipe.addInstructions(recipeInstructions);
        
        if (recipeName.isEmpty() || authorName.isEmpty() || recipeInstructions.isEmpty()) {
        	youScrewedUpTV.setVisibility(View.VISIBLE);
        }
    
        try {
			newRecipe.id = ESClient.getInstance().insertRecipe(newRecipe, true);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       

        recipeCache.createRecipe(newRecipe);
        RecipeSerialization.getInstance(this).makeFile(newRecipe);


        finish();
    }
/**
 * When activity is returned to reopens the cache
 */
   
    public void onResume(){
        recipeCache.open();
        super.onResume();
    }

/**
 * Closes the cache when the activity is left
 */
    public void onPause(){
        recipeCache.close();
        super.onPause();
    }
/**
 * Opens the taking photo class
 * 
 * @param view
 */
    public void newPicture(View view) {
    	Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	
        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
        File folderF = new File(folder);
        if (!folderF.exists()) {
            folderF.mkdir();
        }
        
        imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
        File imageFile = new File(imageFilePath);
        //imageFileUri = Uri.fromFile(imageFile);
        
        //intent.putExtra("photoURI", imageFileUri);
    	
    	startActivityForResult(intent, 2);
    }

}
