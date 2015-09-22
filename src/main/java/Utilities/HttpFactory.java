package Utilities;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class HttpFactory {
	
	private static Logger LOG = LoggerFactory.getLogger(HttpFactory.class);
	HttpClient client;
	HttpResponse response;
	HttpPost post;
	HttpGet get;
	String authToken;
	Integer responseCode;
	String json_string;
	HttpDelete delete;

	public void sendPostRequest(String service, JSONObject json)
			throws ClientProtocolException, IOException {

		String url = System.getProperty("apiUrl") + "/" + service;
		client = new DefaultHttpClient();
		post = new HttpPost(url);
		StringEntity se;
		se = new StringEntity(json.toString());
		se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(se);
		LOG.info("Sending post request for: " + service);
		response = client.execute(post);
		setJson_string(EntityUtils.toString(response.getEntity()));
		setResponseCode(response.getStatusLine().getStatusCode());
		if (getResponseCode() == 200 && service.equals("login")) {
			json = new JSONObject(getJson_string());
			setAuthToken(json.getString("authToken"));
		}
		else {
			setAuthToken("NOT GENERATED");
		}

	}
	
	public void sendGetRequest(String service, String authToken) throws ClientProtocolException, IOException {
		String url = System.getProperty("apiUrl") + "/" + service;
		client = new DefaultHttpClient();
		get = new HttpGet(url);
		get.addHeader("content-type", "application/json");
		get.addHeader("Authorization", "Bearer " + authToken);
		LOG.info("Sending GET request for: " + service);
		response = client.execute(get);
		setResponseCode(response.getStatusLine().getStatusCode());
		if(getResponseCode() == 200) {
			setJson_string(EntityUtils.toString(response.getEntity()));
		}
	}
	
	public void sendDeleteRequest(String service, String authToken) throws ClientProtocolException, IOException {
		String url = System.getProperty("apiUrl") + "/" + service;
		client = new DefaultHttpClient();
		delete = new HttpDelete(url);
		delete.addHeader("content-type", "application/json");
		delete.addHeader("Authorization", "Bearer " + authToken);
		LOG.info("Sending DELETE request for LOGGING OUT");
		response = client.execute(delete);
		setResponseCode(response.getStatusLine().getStatusCode());
		if(getResponseCode() == 204) {
			setAuthToken("NOT GENERATED");;
		}
	}
		

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getJson_string() {
		return json_string;
	}

	public void setJson_string(String json_string) {
		this.json_string = json_string;
	}

}
