package com.dell.dsg.domain;

import java.util.Map;

public class Disk extends Identity {

    public Disk(String pid) {
		super(pid);
	}

	private String name;
	private String diskSize;
	
	public String getDiskSize() {
		return diskSize;
	}
	public void setDiskSize(String diskSize) {
		this.diskSize = diskSize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@SuppressWarnings("rawtypes")
	public Disk mapK1000(Map disk) {
		setId(s_(disk.get("id")));
		setDiskSize(s_(disk.get("size")));
		setName(s_(disk.get("name")));
		return this;
	}

	
}
