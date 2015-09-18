package APIs;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utilities.HttpFactory;

public class GetInventory extends HttpFactory{
	
	private static Logger LOG = LoggerFactory.getLogger(GetInventory.class);
	String json;

	public void getInventory(String hotel_id, String authToken) throws ClientProtocolException, IOException {
		sendGetRequest("inventory?hotelId=" + hotel_id, authToken);
		setJson(getJson_string());
		LOG.info(getJson_string());		
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
