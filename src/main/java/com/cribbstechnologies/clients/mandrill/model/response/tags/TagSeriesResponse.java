package com.cribbstechnologies.clients.mandrill.model.response.tags;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TagSeriesResponse extends BaseMandrillAnonymousListResponse<TagWithTime> {

}
