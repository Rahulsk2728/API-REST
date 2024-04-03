package com.pojo.rest.collection;

import java.util.List;

public class RequestResponse {

	URL  url;

	
	public RequestResponse() {
		
	}
	
	public RequestResponse(URL url, String method , List<Header> header, Body body , String description) {
		super();
		this.url = url;
		
	}
	
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	
	
}
