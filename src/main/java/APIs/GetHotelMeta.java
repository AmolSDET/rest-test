package APIs;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utilities.HttpFactory;

public class GetHotelMeta extends HttpFactory{
	
	private static Logger LOG = LoggerFactory.getLogger(GetHotelMeta.class);
	
	public void getHotelMetadata(String hotelId, String authToken) throws ClientProtocolException, IOException {
		sendGetRequest("hotel-metadata/" + hotelId, authToken);
		LOG.info(getJson_string());
		Assert.assertTrue("hotel meta-data request didn't go through", getResponseCode() == 200);
	}

}
