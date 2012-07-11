package com.cribbstechnologies.clients.mandrill.it;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cribbstechnologies.clients.mandrill.model.*;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import com.cribbstechnologies.clients.mandrill.model.MandrillTemplatedMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.MergeVar;
import com.cribbstechnologies.clients.mandrill.model.TemplateContent;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.response.message.SendMessageResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillMessagesRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;

public class MessagesTest {
	
	private static MandrillRESTRequest request = new MandrillRESTRequest();
	private static MandrillConfiguration config = new MandrillConfiguration();
	private static MandrillMessagesRequest messagesRequest = new MandrillMessagesRequest();
	private static HttpClient client;
	private static ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void beforeClass() {
		config.setApiKey("b9fe9170-08f7-4f56-b0d1-e56ce1e426e7");
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
		request.setConfig(config);
		request.setObjectMapper(mapper);
		messagesRequest.setRequest(request);
	}
	
	@Before 
	public void before() {
		client = new DefaultHttpClient();
		request.setHttpClient(client);
	}
	
//	@Test
//	public void testSendMessage() {
//		MandrillMessageRequest mmr = new MandrillMessageRequest();
//		MandrillHtmlMessage message = new MandrillHtmlMessage();
//		Map<String, String> headers = new HashMap<String, String>();
//		message.setFrom_email("peter.backx@fctr.be");
//		message.setFrom_name("Peter Backx");
//		message.setHeaders(headers);
//		message.setHtml("<html><body><h1>Oh snap!</h1>Guess what I saw?<a href=\"http://www.google.com\">google</a></body></html>");
//		message.setSubject("This is the subject");
//		MandrillRecipient[] recipients = new MandrillRecipient[]{new MandrillRecipient("Peter Backx", "peter.backx@gmail.com")}; //, new MandrillRecipient("Brian Cribbs", "brian@cribbstechnologies.com")};
//		message.setTo(recipients);
//		message.setTrack_clicks(true);
//		message.setTrack_opens(true);
//		String[] tags = new String[]{"tag1", "tag2", "tag3"};
//		message.setTags(tags);
//		mmr.setMessage(message);
//		
//		try {
//			SendMessageResponse response = messagesRequest.sendMessage(mmr);
//		} catch (RequestFailedException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
	
	@Test
	public void testSendTemplatedMessage() {
		MandrillTemplatedMessageRequest request = new MandrillTemplatedMessageRequest();
		MandrillMessage message = new MandrillMessage();
		Map<String, String> headers = new HashMap<String, String>();
		message.setFrom_email("peter.backx@fctr.be");
		message.setFrom_name("Peter Backx");
		message.setHeaders(headers);
		message.setSubject("Nieuw wachtwoord voor FCTR.be");
		MandrillRecipient[] recipients = new MandrillRecipient[]{new MandrillRecipient("Peter Backx", "peter.backx@gmail.com")}; //, new MandrillRecipient("Brian Cribbs", "brian@cribbstechnologies.com")};
		message.setTo(recipients);
		message.setTrack_clicks(true);
		message.setTrack_opens(true);
		request.setMessage(message);
		List<TemplateContent> content = new ArrayList<TemplateContent>();
		request.setTemplate_content(content);
		request.setTemplate_name("fctr_new_password");
		
        List<MergeVar> globalMergeVars = new ArrayList<MergeVar>();
        globalMergeVars.add(new MergeVar("NEWPWD", "test"));
        message.setGlobal_merge_vars(globalMergeVars);

        try {
			messagesRequest.sendTemplatedMessage(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
