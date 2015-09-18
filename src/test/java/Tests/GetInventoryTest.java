package Tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.junit.Test;

import Utilities.HttpFactory;
import APIs.GetInventory;

public class GetInventoryTest {
	
	@Test
	public void validateGetInventory() throws ClientProtocolException, IOException {
		HttpFactory http = new HttpFactory();
		JSONObject json = new JSONObject();
		json.put("emailAddress", "xyz@hotwire.com");
		json.put("password", "xyz");
		http.sendPostRequest("login", json);
		String authToken = http.getApiKey();
		new GetInventory().getInventory("1000", authToken);
	}

}
