package com.pojo.rest.collection;

import java.util.List;

public class CollectionResponse extends CollectionBase {
   
    List<Object> item;
    
    public CollectionResponse () {	        
         }
    
public CollectionResponse (Info info,List<Object> item) {
	super(info);
	this.item = item;
}


public List<Object> getItem() {
	return item;
}
public void setFolderList(List<Object> item) {
	this.item = item;
}

}
