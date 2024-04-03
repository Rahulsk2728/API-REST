package com.pojo.rest.workspace;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(value = "id", allowSetters = true)
public class Workspace {
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int i;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id ;
	private String name;
	private String type;
	private String description;
	
	public Workspace(String name , String type, String description) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return name;
	}
	
	public String getDescription() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public void setType(String type) {
		this.type= type;
	}
	
	public void setDescription(String description) {
	     this.description = description;
	}

	public void setId(String id) {
	     this.id = id;
	}
}
