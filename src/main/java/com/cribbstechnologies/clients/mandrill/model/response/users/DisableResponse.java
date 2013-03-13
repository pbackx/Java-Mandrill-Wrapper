package com.cribbstechnologies.clients.mandrill.model.response.users;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DisableResponse extends BaseMandrillResponse {

	String domain;
	String created_at;
	String approved_at;
	boolean is_enabled;
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getApproved_at() {
		return approved_at;
	}
	
	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}
	
	public boolean isIs_enabled() {
		return is_enabled;
	}
	
	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
}
