package com.dell.dsg.adapter;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dell.dsg.adapter.k1000.K1000Adapter;

//TODO Spring factory bean
/**
 * @author vtihomir
 */
@Component
public class ComputerSystemFactory {

	@Resource(name = "adapterProps")
	private Properties props;
	
	/**
	 * @param pid
	 * @return implementation of adapter by pid
	 */
	public ComputerSystemAdapter getAdapter(String pid) {
		if (!StringUtils.hasText(props.getProperty(pid + ".host"))) {
			throw new UnsupportedOperationException("Product id " + pid + " is not supported");
		}
		return new K1000Adapter(pid, props);
	}
}
