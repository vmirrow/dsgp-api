package com.dell.dsg.domain;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;


public class ComputerSystemBase extends Identity {
	private String name;
	private String domain;
	private String osName;

	public ComputerSystemBase(String prodId) {
		super(prodId);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}

	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
	
	@SuppressWarnings("rawtypes")
	public void mapK1000(Map comp) {
		//TODO data model for k1000? 
		setId(s_(comp.get("ID")));
		setName(s_(comp.get("NAME")));
		setDomain("domain");
		setOsName(s_(comp.get("OS_NAME")));
	}
}
