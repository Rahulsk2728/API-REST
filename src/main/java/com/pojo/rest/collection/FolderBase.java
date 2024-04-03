package com.pojo.rest.collection;

import java.util.List;

public abstract class FolderBase {

	private String name;

	public FolderBase() {
		
	}
	
	public FolderBase(String name ) {
		this.name = name;
	
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
