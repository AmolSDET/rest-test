package Tests;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import APIs.GetHotelMeta;
import Utilities.HttpFactory;
import Utilities.Utils;

public class GetHotelMetaTest {
	
	private static Logger LOG=LoggerFactory.getLogger(GetHotelMetaTest.class);
	
	
	@Test
	public void getHotelMetadataTest() throws ClientProtocolException, IOException {
		HttpFactory http = new HttpFactory();
		JSONObject json = new JSONObject();
		json.put("emailAddress", "xyz@gmail.com");
		json.put("password", "xyz");
		http.sendPostRequest("login", json);
		String authToken = http.getAuthToken();
		//hotel-id
		new GetHotelMeta().getHotelMetadata("0000", authToken);
	}
	
	@Test
	public void validateHotelMetadataTest() throws ClientProtocolException, IOException {
		HttpFactory http = new HttpFactory();
		JSONObject json = new JSONObject();
		json.put("emailAddress", "xyz@gmail.com.com");
		json.put("password", "xyz");
		http.sendPostRequest("login", json);
		String authToken = http.getAuthToken();
		//send hotel-id
		http.sendGetRequest("hotel-metadata/0000", authToken);
		json = new JSONObject(http.getJson_string());
		List<String> elements = new Utils().getHotelMetadataObjects();
		for(String element: elements) {
			LOG.info("Validating " + element);
			String[] jsonItem = element.split("--");
			Assert.assertTrue(element + " is incorrect", json.get(jsonItem[0]).toString().contains(jsonItem[1]) );
		}
	}

}
