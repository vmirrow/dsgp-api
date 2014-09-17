package com.dell.dsg.domain;


public class Identity extends Mapable {

	private String id;
	private String pid;
	
	public Identity(String pid) {
		super();
		this.pid = pid;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
