package com.pojo.rest.collection;

import java.util.List;

public class RequestRequest extends RequestBase{

	private String url;

	public RequestRequest() {
		
	}
	
	public RequestRequest(String url, String method , List<Header> header, Body body , String description) {
		super(method ,description, header ,body ,description);
		this.url = url;
		
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
