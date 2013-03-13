package com.cribbstechnologies.clients.mandrill.model.response.templates;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TemplateListResponse extends BaseMandrillAnonymousListResponse<TemplateResponse> {

}
