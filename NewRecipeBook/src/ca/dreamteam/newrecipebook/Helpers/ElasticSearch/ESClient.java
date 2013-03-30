package ca.dreamteam.newrecipebook.Helpers.ElasticSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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

	public void insertRecipe(Recipe recipe) throws IOException, IllegalStateException{
		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/testing/"+recipe.getId());
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
		//BufferedReader bReader = new BufferedReader(new InputStreamReader(entity.getContent()));
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
		HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w13t01/recipelistlength/value");
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

		return Integer.parseInt(json.split("Number")[1].replace("\"", "")
				.replace(":", "").replace("}", ""));
	}

	
	//The following function was borrowed from our friends in team 9, who probably took it from the ESClient d
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
