package com.pojo.rest.collection;

public class RequestRootRequest extends RequestRootBase {

	
	RequestBase request;
	
	public RequestRootRequest(String name, RequestBase request) {
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
