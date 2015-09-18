package Tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

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
		new LoginAPI().sendLoginRequest("xyz122@gmail.com", "xyz",
				"invalid");
	}
}
