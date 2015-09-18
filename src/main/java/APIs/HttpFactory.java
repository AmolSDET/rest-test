package APIs;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class HttpFactory {

	HttpClient client;
	HttpResponse response;
	HttpPost post;
	HttpGet get;
	String apiKey;
	Integer responseCode;

	public void sendPostRequest(String service, JSONObject json)
			throws ClientProtocolException, IOException {

		String url = System.getProperty("apiUrl") + "/" + service;
		client = new DefaultHttpClient();
		post = new HttpPost(url);
		StringEntity se;
		se = new StringEntity(json.toString());
		se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(se);
		response = client.execute(post);
		String json_string;
		json_string = EntityUtils.toString(response.getEntity());
		setResponseCode(response.getStatusLine().getStatusCode());
		if (getResponseCode() == 200 && service.equals("login")) {
			json = new JSONObject(json_string);
			setApiKey(json.getString("authToken"));
		}
		else {
			setApiKey("NOT GENERATED");
		}

	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

}