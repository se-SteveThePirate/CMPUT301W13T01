package ca.dreamteam.newrecipebook.Helpers;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

//This code was taken from our good friends on Team 08 in CMPUT301W13, Thanks Rob!!!!
//The original website and code can be found in their references

//http://mobile.cs.fsu.edu/converting-images-to-json-objects/
//here is the website for quick references.

public class ImageConverter
{

    public String getJsonString(Bitmap bitmapPicture) {
        String encodedImage = getStringFromBitmap(bitmapPicture);
        JSONObject jsonObj = null;

        try {
            // getting the JSON object from String with the key as "image"
            jsonObj = new JSONObject("{\"image\":\"" + encodedImage + "\"}");
        } catch (JSONException e) {
            System.out.println("Error in method getJson: " + e);
            e.printStackTrace();
        }

        String jasonString = null;
        try {
            jasonString = (String) jsonObj.get("image");
        } catch (JSONException e) {
            System.out.println("Error in getJsonObjectFromString method: " + e);
            e.printStackTrace();
        }
        return jasonString;
    }


    public static String getStringFromBitmap(Bitmap bitmapPicture) {
        /*
         * This functions converts Bitmap picture to a string which can be JSONified.
         */
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;

    }

    public Bitmap getBitmapFromString(String jsonString) {
        /*
         * This Function converts the String back to Bitmap
         */
        byte[] decodedString = Base64.decode(jsonString, Base64.DEFAULT);

        // decoding bytes here.
        Bitmap decodedStringNowBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedStringNowBitmap;
    }



}
