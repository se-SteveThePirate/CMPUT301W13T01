package ca.dreamteam.newrecipebook.Helpers.ElasticSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import ca.dreamteam.newrecipebook.Models.Recipe;

import com.google.gson.Gson;

//This code was modified from it's original form as hosted on : https://github.com/rayzhangcl/ESDemo
public class ESClient {

	private static ESClient singletonInstance = null;

	protected ESClient(){
		//Stops all instantiation
	}

	public static ESClient getInstance(){
		if (singletonInstance == null)
			singletonInstance = new ESClient();

		return singletonInstance;
	}

	private HttpClient httpClient = new DefaultHttpClient();

	private Gson gson = new Gson();

	/**
	 * Adapted for our use from the ESDemo code.
	 * @param recipe
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public void deleteRecipe(Recipe recipe) throws ClientProtocolException, IOException
	{
		HttpDelete httpDelete = new HttpDelete("http://cmput301.softwareprocess.es:8080/cmput301w13t01/recipelist/" + recipe.getId());
		httpDelete.addHeader("Accept","application/json");

		HttpResponse response = httpClient.execute(httpDelete);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}
		//EntityUtils.consume(entity);

		httpDelete.releaseConnection();
	}

	/**
	 * Updates a recipe already posted on ES.
	 * 
	 * @param recipe
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void updateRecipe(Recipe recipe) throws IllegalStateException, IOException{
		insertRecipe(recipe, false);
	}

	public void insertRecipe(Recipe recipe, Boolean requiresID) throws IOException, IllegalStateException{
		//Set the ES ID to the next available ID number. (Avoid conflicts :) )
		if(requiresID){
			recipe.id = getNextAvailableId();
			setNextAvailableId(recipe.id + 1);
		}

		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t01/recipelist/"+recipe.getId());
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(gson.toJson(recipe));
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringEntity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpEntity entity = response.getEntity();
		try {
			EntityUtils.consume(entity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public int getNextAvailableId()
	{
		//Code borrowed from our friends in team 9, as they posted here: 
		//https://github.com/kylejamesross/CMPUT301W13T09/blob/master/CMPUT301Project/src/com/cmput301w13t09/cmput301project/UploadController.java
		HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w13t01/recipelist/nextID");
		getRequest.addHeader("Content-type", "application/json");
		String json = "";
		try
		{
			HttpResponse response = httpClient.execute(getRequest);
			json = getEntityContent(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//String[] jsonSplit = json.split("nextID");
		//String importantValue = jsonSplit[2]; //":1 }}

		return Integer.parseInt(json.split("nextID")[2].replace("\"", "").replace(":", "").replace("}", "").replace(" ", ""));
	}

	public void setNextAvailableId(long id){
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301w13t01/recipelist/nextID");
		httpPost.setHeader("Content-type", "application/json");
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(new JSONObject().put("nextID", id)
					.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//The following function was borrowed from our friends in team 9, who probably took it from the ESClient demo.
	public String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:" + json);
		return json;
	}
}
