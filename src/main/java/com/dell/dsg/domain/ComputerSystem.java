package com.dell.dsg.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.util.CollectionUtils;

public class ComputerSystem extends ComputerSystemBase {
	private boolean virtual;
	private String osVersion;
	private String osArchitecture;
	private String osServicePack;
	private Date installDate;
	private String biosName;
	private String biosVersion;
	private String manufacture;
	private String model;
	private String processor;
	private Long ramTotal;
	private Set<Disk> disks; 

	public ComputerSystem(String pid) {
		super(pid);
	}
	
	public boolean isVirtual() {
		return virtual;
	}
	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getOsArchitecture() {
		return osArchitecture;
	}
	public void setOsArchitecture(String osArchitecture) {
		this.osArchitecture = osArchitecture;
	}
	public String getOsServicePack() {
		return osServicePack;
	}
	public void setOsServicePack(String osServicePack) {
		this.osServicePack = osServicePack;
	}
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	public String getBiosName() {
		return biosName;
	}
	public void setBiosName(String biosName) {
		this.biosName = biosName;
	}
	public String getBiosVersion() {
		return biosVersion;
	}
	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public Long getRamTotal() {
		return ramTotal;
	}
	public void setRamTotal(Long ramTotal) {
		this.ramTotal = ramTotal;
	}
	

	public Set<Disk> getDisks() {
		return disks;
	}

	public void setDisks(Set<Disk> disks) {
		this.disks = disks;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void mapK1000(Map info) {
		Map comp = (Map) info.get("machine");
		Map os = (Map) comp.get("operating_system");
		Map hardware = (Map) comp.get("hardware");
		//TODO why it has different keys? super.mapK1000(comp);
		setId(s_(comp.get("id")));
		setName(s_(comp.get("name")));
		setOsName(s_(comp.get("os_name")));
		setDomain(s_(os.get("Domain")));
		setOsArchitecture(s_(os.get("Architecture")));
		setOsVersion(s_(os.get("Version")));
		setBiosName(s_(hardware.get("BIOS Name")));
		setBiosVersion(s_(hardware.get("BIOS Version")));
		setModel(s_(hardware.get("Model")));
		setManufacture(s_(hardware.get("Manufacturer")));
		setProcessor(s_(hardware.get("Processors")));
		List<Map> diskMap = (List<Map>) comp.get("disks");
		if (!CollectionUtils.isEmpty(diskMap)) {
			 this.disks = new HashSet<Disk>();
			for (Map disk : diskMap) {
				this.disks.add(new Disk(getPid()).mapK1000(disk));
			}
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
	
}
