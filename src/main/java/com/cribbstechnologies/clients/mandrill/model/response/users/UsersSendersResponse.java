package com.cribbstechnologies.clients.mandrill.model.response.users;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UsersSendersResponse extends BaseMandrillAnonymousListResponse<MandrillSender> {

}
