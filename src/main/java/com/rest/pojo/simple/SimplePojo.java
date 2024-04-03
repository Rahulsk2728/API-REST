package com.rest.pojo.simple;

public class SimplePojo {

	public SimplePojo (String key1  ,String key2) {
		this.key1 = key1;
		this.key2 = key2;
	}
	
	private String key1;
	private String key2;
	
	public String getkey1() {
		return key1;
	}
	
	public void setkey1(String key1) {
		this.key1 = key1;
	}
	
	public String getkey2() {
		return key2;
		
		}
		public void setkey2(String key2) {
			this.key2 = key2;
	}
}
