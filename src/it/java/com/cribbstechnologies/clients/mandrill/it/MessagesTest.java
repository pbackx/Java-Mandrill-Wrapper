package com.cribbstechnologies.clients.mandrill.it;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.cribbstechnologies.clients.mandrill.model.*;

import org.apache.commons.codec.binary.Base64OutputStream;
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
import com.cribbstechnologies.clients.mandrill.util.AttachmentEncoder;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;

public class MessagesTest {
	
	private static MandrillRESTRequest request = new MandrillRESTRequest();
	private static MandrillConfiguration config = new MandrillConfiguration();
	private static MandrillMessagesRequest messagesRequest = new MandrillMessagesRequest();
	private static HttpClient client;
	private static ObjectMapper mapper = new ObjectMapper();
	private static Properties props = new Properties();
	
	@BeforeClass
	public static void beforeClass() {
		try {
			props.load(MessagesTest.class.getClassLoader().getResourceAsStream("mandrill.properties"));
		} catch (IOException e) {
			fail ("properties file not loaded");
		}
		config.setApiKey(props.getProperty("apiKey"));
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
		request.setConfig(config);
		request.setObjectMapper(mapper);
		messagesRequest.setRequest(request);
	}
	
	@Before 
	public void before() {
		client = new DefaultHttpClient();
//		request.setHttpClient(client);
	}
	
	@Test
	public void testSendMessage() {
		MandrillMessageRequest mmr = new MandrillMessageRequest();
		MandrillHtmlMessage message = new MandrillHtmlMessage();
		Map<String, String> headers = new HashMap<String, String>();
		message.setFrom_email(props.getProperty("email.from"));
		message.setFrom_name("Big Jimmy");
		message.setHeaders(headers);
		message.setHtml("<html><body><h1>Oh snap!</h1>Guess what I saw?<a href=\"http://www.google.com\">google</a></body></html>");
		message.setSubject("This is the subject");
		MandrillRecipient[] recipients = new MandrillRecipient[]{new MandrillRecipient(props.getProperty("email.to.name1"), props.getProperty("email.to.address1")), new MandrillRecipient(props.getProperty("email.to.name2"), props.getProperty("email.to.address2"))};
		message.setTo(recipients);
		message.setBcc_address(props.getProperty("email.bcc.address"));
		message.setTrack_clicks(true);
		message.setTrack_opens(true);
		String[] tags = new String[]{"tag1", "tag2", "tag3"};
		message.setTags(tags);
		mmr.setMessage(message);
		
		try {
			SendMessageResponse response = messagesRequest.sendMessage(mmr);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSendTemplatedMessage() throws Exception {
		MandrillTemplatedMessageRequest request = new MandrillTemplatedMessageRequest();
		MandrillMessage message = new MandrillMessage();
		Map<String, String> headers = new HashMap<String, String>();
		message.setFrom_email(props.getProperty("email.from"));
		message.setFrom_name("Big Jimmy");
		message.setHeaders(headers);
		message.setSubject("This is the subject");
		MandrillRecipient[] recipients = new MandrillRecipient[]{new MandrillRecipient(props.getProperty("email.to.name1"), props.getProperty("email.to.address1")), new MandrillRecipient(props.getProperty("email.to.name2"), props.getProperty("email.to.address2"))};
		message.setTo(recipients);
		message.setTrack_clicks(true);
		message.setTrack_opens(true);
		request.setMessage(message);
		List<TemplateContent> content = new ArrayList<TemplateContent>();
		request.setTemplate_content(content);
//		request.setTemplate_name("fctr_new_password");
		request.setTemplate_name("fctr_activation");
		
        List<MergeVar> globalMergeVars = new ArrayList<MergeVar>();
        //globalMergeVars.add(new MergeVar("NEWPWD", "test"));
        globalMergeVars.add(new MergeVar("ACTURL", "http://www.google.com/"));
        message.setGlobal_merge_vars(globalMergeVars);
        
        List<Attachment> attachments = new ArrayList<Attachment>();
        attachments.add(new Attachment(
        						"application/pdf", 
        						"factuur.pdf", 
        						new AttachmentEncoder(getClass().getResourceAsStream("/attachment/invoice.pdf")).getEncodedString()
        						));
        message.setAttachments(attachments);
        
        try {
			messagesRequest.sendTemplatedMessage(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
