package com.cribbstechnologies.clients.mandrill.it;

import static org.junit.Assert.fail;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithQuery;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithUrl;
import com.cribbstechnologies.clients.mandrill.model.response.urls.UrlListResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillUrlsRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;

public class UrlsTest {
	
	private static MandrillRESTRequest request = new MandrillRESTRequest();
	private static MandrillConfiguration config = new MandrillConfiguration();
	private static MandrillUrlsRequest urlsRequest = new MandrillUrlsRequest();
	private static HttpClient client;
	private static ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void beforeClass() {
		config.setApiKey("a2d9f0f9-5646-4af2-a507-76758c71631b");
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
		request.setConfig(config);
		request.setObjectMapper(mapper);
		urlsRequest.setRequest(request);
	}
	
	@Before 
	public void before() {
		client = new DefaultHttpClient();
//		request.setHttpClient(client);
	}

	@Test
	public void testGetList() {
		BaseMandrillRequest request = new BaseMandrillRequest();
		try {
			UrlListResponse response = urlsRequest.getList(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testQuery() {
		MandrillRequestWithQuery request = new MandrillRequestWithQuery();
		request.setQ("http://www.google.com");
		try {
			UrlListResponse response = urlsRequest.doSearch(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetTimeSeries() {
		MandrillRequestWithUrl request = new MandrillRequestWithUrl();
		request.setUrl("http://www.google.com");
		try {
			UrlListResponse response = urlsRequest.getTimeSeries(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
