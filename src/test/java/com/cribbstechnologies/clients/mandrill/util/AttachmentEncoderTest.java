package com.cribbstechnologies.clients.mandrill.util;

import org.junit.Test;

public class AttachmentEncoderTest {

	@Test
	public void test() throws Exception {
		System.out.println(new AttachmentEncoder(getClass().getResourceAsStream("/attachment/invoice.pdf")).getEncodedString());
	}
}
