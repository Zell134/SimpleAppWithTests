package com.mycompany.simpleapp.services;

import java.util.Date;

class DataSearchRequest {
	String id;

	Date updatedBefore;

	int length;

	public DataSearchRequest() {
		super();
	}

	public DataSearchRequest(String id, Date updatedBefore, int length) {
		super();
		this.id = id;
		this.updatedBefore = updatedBefore;
		this.length = length;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdatedBefore() {
		return updatedBefore;
	}

	public void setUpdatedBefore(Date updatedBefore) {
		this.updatedBefore = updatedBefore;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
