package com.cribbstechnologies.clients.mandrill.model.response.urls;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UrlTimeResponse extends BaseMandrillAnonymousListResponse<TimeUrlResponse> {

}
