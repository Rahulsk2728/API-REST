package com.pojo.rest.collection;

import java.util.List;

public class FolderResponse extends FolderBase {

	
	List<RequestRootBase> item;
	
	public FolderResponse() {
		
	}
	
	public FolderResponse(String name , List<RequestRootBase> item) {
		super(name);
		this.item = item;
	}
	
	
	public List<RequestRootBase> getItem() {
		return item;
	}
	
	public void setItem(List<RequestRootBase> item) {
		this.item = item;
	}
}
