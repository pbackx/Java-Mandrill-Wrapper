package com.cribbstechnologies.clients.mandrill.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64OutputStream;

public class AttachmentEncoder {

	private static final int BUFFER_SIZE = 4096;
	
	private InputStream input;

	public AttachmentEncoder(InputStream input) {
		this.input = input;
	}
	
	public String getEncodedString() throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		Base64OutputStream base64 = new Base64OutputStream(bytes);
		int n = input.read(buffer, 0, BUFFER_SIZE);
		while(n>=0) {
			base64.write(buffer, 0, n);
			n = input.read(buffer, 0, BUFFER_SIZE);
		}
		input.close();
		base64.close();
		return bytes.toString("UTF-8");
	}
}
