package com.dell.dsg.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dell.dsg.adapter.domain.Product;
import com.dell.dsg.adapter.k1000.K1000AdapterImpl;

//TODO Spring factory bean
/**
 * @author vtihomir
 */
@Component
public class ComputerSystemFactory {

	@Resource(name = "adapterProps")
	private Properties props;

	/**
	 * @return all active products
	 */
	public List<Product> getActiveProducts() {
		String strPids = props.getProperty("pids.active");
		if (!StringUtils.hasText(strPids)) {
			throw new UnsupportedOperationException("No active products configured");
		}
		String[] pids = strPids.split(",");
		List<Product> products = new ArrayList<Product>();
		for (String pid : pids) {
			String tpid = pid.trim();
			products.add(new Product(tpid, 
					props.getProperty(tpid + ".type"),
					props.getProperty(tpid + ".host")));
		}
		return products;
	}

	/**
	 * @param pid
	 * @return implementation of adapter by pid
	 */
	public ComputerSystemAdapter getAdapter(String pid) {
		if (!StringUtils.hasText(props.getProperty(pid + ".host"))) {
			throw new UnsupportedOperationException("Product id " + pid
					+ " is not supported");
		}
		return new K1000AdapterImpl(pid, props);
	}
}
