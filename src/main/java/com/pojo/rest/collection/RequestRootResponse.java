 package com.pojo.rest.collection;

public class RequestRootResponse extends RequestRootBase {

	
	RequestBase request;
	
	
	public RequestRootResponse(String name, RequestBase request) {
		
		super(name);
		this.request = request;
	}
	
	public RequestBase getRequest() {
		return request;
	}
	public void setRequest(RequestBase request) {
		this.request = request;
	}
	
}
