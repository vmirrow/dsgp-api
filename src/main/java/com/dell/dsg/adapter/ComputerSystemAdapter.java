package com.dell.dsg.adapter;

import java.util.List;

import com.dell.dsg.domain.ComputerSystem;
import com.dell.dsg.domain.ComputerSystemBase;

/**
 * Should be implemented by all ComputerSystem Adapters 
 * @author vtihomir
 */
public interface ComputerSystemAdapter {
	
	/**
	 * @return all computer systems
	 */
	public List<ComputerSystemBase> getComputerSystems();
	
	/**
	 * @param id
	 * @return computer system by id
	 */
	public ComputerSystem getComputerById(String id);

}
