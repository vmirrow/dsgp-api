package com.dell.dsg.adapter;

import java.util.List;

import com.dell.dsg.domain.ComputerSystem;
import com.dell.dsg.domain.ComputerSystemBase;

public interface ComputerSystemAdapter {
	
	public List<ComputerSystemBase> getComputerSystems(); 
	public ComputerSystem getComputerById(String id);

}
