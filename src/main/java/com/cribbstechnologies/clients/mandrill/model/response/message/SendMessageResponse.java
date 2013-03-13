package com.cribbstechnologies.clients.mandrill.model.response.message;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SendMessageResponse extends BaseMandrillAnonymousListResponse<MessageResponse> {

}
