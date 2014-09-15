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
	 * @return
	 */
	public List<ComputerSystemBase> getComputerSystems();
	
	/**
	 * @param id
	 * @return
	 */
	public ComputerSystem getComputerById(String id);

}
