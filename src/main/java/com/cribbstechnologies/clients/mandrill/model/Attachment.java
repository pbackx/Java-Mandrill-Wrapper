package com.cribbstechnologies.clients.mandrill.model;

public class Attachment {

	private String type;
	private String name;
    private String content;
	
    public Attachment(String type, String name, String content) {
		super();
		this.type = type;
		this.name = name;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
