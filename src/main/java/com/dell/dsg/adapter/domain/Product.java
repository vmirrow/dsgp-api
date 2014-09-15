package com.dell.dsg.adapter.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Product {
	private String pid;
	private String type;
	private String host;
	
	
	public Product(String pid, String type, String host) {
		super();
		this.pid = pid;
		this.type = type;
		this.host = host;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
	
	
}
