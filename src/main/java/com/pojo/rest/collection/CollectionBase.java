package com.pojo.rest.collection;

import java.util.List;

public class CollectionBase {
    Info info;
  
    
    public CollectionBase () {	        
         }
    
public CollectionBase (Info info) {
	this.info = info;

}


public Info getInfo() {
	return info;
}
public void setInfo(Info info) {
	this.info = info;
}


}
