package com.pojo.rest.collection;

import java.util.List;

public class CollectionRootResponse extends CollectionRootBase{
CollectionBase collection;

public CollectionRootResponse(CollectionBase collection) {
	this.collection = collection;
}

public CollectionBase getCollection() {
	return collection;
}

public void setCollection(CollectionBase collection) {
	this.collection = collection;
}
	
}
