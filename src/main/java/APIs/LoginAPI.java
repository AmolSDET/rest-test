package APIs;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAPI extends HttpFactory {

	private static Logger LOG = LoggerFactory.getLogger(LoginAPI.class);

	public void sendLoginRequest(String emailAddress, String password,
			String user) throws ClientProtocolException, IOException {
		JSONObject json = new JSONObject();
		json.put("emailAddress", emailAddress);
		json.put("password", password);
		sendPostRequest("login", json);
		LOG.info("API KEY:-" + getApiKey());
		LOG.info("RESPONSE CODE:- " + getResponseCode());
		if(user.toLowerCase().equals("valid")) {
			Assert.assertTrue("API KEY IS NOT GENERATED", !getApiKey().equals("NOT GENERATED"));
			Assert.assertTrue("RESPONSE CODE IS NOT CORRECT", getResponseCode() == 200);
		}
		else if(user.toLowerCase().equals("invalid")){
			Assert.assertTrue("API KEY IS GENERATED", getApiKey().equals("NOT GENERATED"));
			Assert.assertTrue("API KEY IS NOT CORRECT", getResponseCode() == 401);
		}
	}

}
