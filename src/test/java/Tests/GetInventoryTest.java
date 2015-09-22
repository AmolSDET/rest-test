package Tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Utilities.HttpFactory;
import Utilities.Utils;
import APIs.GetInventory;

public class GetInventoryTest {
	
	private static Logger LOG = LoggerFactory.getLogger(GetInventoryTest.class);
	
	
	@Test
	public void validateGetInventory() throws ClientProtocolException, IOException {
		HttpFactory http = new HttpFactory();
		JSONObject json = new JSONObject();
		json.put("emailAddress", "xyz@gmail.com");
		json.put("password", "xyz");
		http.sendPostRequest("login", json);
		String authToken = http.getAuthToken();
		//hotelid
		new GetInventory().getInventory("0000", authToken);
	}
	
	@Test
	public void validLogout() throws ClientProtocolException, IOException {
		HttpFactory http = new HttpFactory();
		JSONObject json = new JSONObject();
		json.put("emailAddress", "xyz@gmail.com");
		json.put("password", "0000");
		http.sendPostRequest("login", json);
		String authToken = http.getAuthToken();
		http.sendDeleteRequest("login", authToken);
		http.sendGetRequest("inventory?hotelId=0000", authToken);
		Assert.assertTrue("Logout is incorrect", http.getResponseCode() == 401);
	}
	
	@Test
	public void validateGetInventoryResponse() throws ClientProtocolException, IOException {
		HttpFactory http = new HttpFactory();
		JSONObject json = new JSONObject();
		json.put("emailAddress", "xyz@gmail.com");
		json.put("password", "xyz");
		http.sendPostRequest("login", json);
		String authToken = http.getAuthToken();
		http.sendGetRequest("inventory?hotelId=0000", authToken);
		json = new JSONObject(http.getJson_string());
		LOG.info("Asserting GET INVENTORY response");
		Assert.assertTrue("Todays date is incorrect", json.get("today").toString().equals(new Utils().getDate(0)));
		Assert.assertTrue("hotel id is incorrect", json.get("hotelId").toString().equals("0000"));
		Assert.assertTrue("Inventory is not valid. Length is: " + json.getJSONArray("stayDateRateList").length(), json.getJSONArray("stayDateRateList").length() == 14);
		JSONArray array = json.getJSONArray("stayDateRateList");
		for(int i=0; i<array.length(); i++) {
			LOG.info("asserting json object: - " + array.getJSONObject(i).toString());
			Assert.assertTrue("stay is incorrect", array.getJSONObject(i).get("stayDate").toString().equals(new Utils().getDate(i)));
		}
	}

}
