package com.pojo.rest.collection;

import java.util.List;

public class URL {
    
	public URL(String raw, String protocol, List<String> host, List<String> path) {
		super();
		this.raw = raw;
		this.protocol = protocol;
		this.host = host;
		this.path = path;
	}
	
	
	private String raw;
	private String protocol;
	private List<String> host;
	private List<String> path;
     
	
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public List<String> getHost() {
		return host;
	}
	public void setHost(List<String> host) {
		this.host = host;
	}
	public List<String> getPath() {
		return path;
	}
	public void setPath(List<String> path) {
		this.path = path;
	}
	
	
	
	

   }
