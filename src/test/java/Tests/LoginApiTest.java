package Tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.junit.Test;

import Utilities.HttpFactory;
import APIs.GetInventory;
import APIs.LoginAPI;

/**
 * Unit test for simple Api.
 */
public class LoginApiTest {

	@Test
	public void testValidLogin() throws ClientProtocolException, IOException {
		new LoginAPI().sendLoginRequest("xyz@gmail.com", "xyz",
				"valid");
	}

	@Test
	public void testInvalidLogin() throws ClientProtocolException, IOException {
		new LoginAPI().sendLoginRequest("xyz@gmail.com", "xyz",
				"invalid");
	}
	
	@Test
	public void testLoginWithCaps() throws ClientProtocolException, IOException {
		new LoginAPI().sendLoginRequest("xyz@gmail.com", "xyz",
				"invalid");
	}
	
	@Test
	public void validateToken() throws ClientProtocolException, IOException {
		HttpFactory http = new HttpFactory();
		JSONObject json = new JSONObject();
		json.put("emailAddress", "xyz@gmail.com");
		json.put("password", "xyz");
		http.sendPostRequest("login", json);
		String authToken = http.getAuthToken();
		new GetInventory().getInventory("0000", authToken);
	}
}
