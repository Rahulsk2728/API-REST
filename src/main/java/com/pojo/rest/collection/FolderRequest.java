package com.pojo.rest.collection;

import java.util.List;

public  class FolderRequest extends FolderBase{

	List<RequestRootRequest> item;

	
	public List<RequestRootRequest> getItem() {
		return item;
	}

	public void setItem(List<RequestRootRequest> item) {
		this.item = item;
	}

	public FolderRequest() {
		
	}
	
	public FolderRequest(String name, List<RequestRootRequest> item ) {
		super(name);
		this.item = item;
		
	}
	
	

}
