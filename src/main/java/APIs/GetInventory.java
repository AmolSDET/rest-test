package APIs;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utilities.HttpFactory;

public class GetInventory extends HttpFactory{
	
	private static Logger LOG = LoggerFactory.getLogger(GetInventory.class);

	public void getInventory(String hotel_id, String authToken) throws ClientProtocolException, IOException {
		sendGetRequest("inventory?hotelId=" + hotel_id, authToken);
		LOG.info(getJson_string());		
		Assert.assertTrue("get inventory requests didn't go through", getResponseCode() == 200);
	}

}
