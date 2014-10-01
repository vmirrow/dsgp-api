package com.dell.dsg.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dell.dsg.adapter.domain.Product;
import com.dell.dsg.adapter.k1000.K1000AdapterImpl;
import com.dell.dsg.adapter.konea.KoneaK1000AdapterImpl;

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
			throw new NotFoundException("No active products configured");
		}
		String[] pids = strPids.split(",");
		List<Product> products = new ArrayList<Product>();
		for (String pid : pids) {
			products.add(getProduct(pid.trim()));
		}
		return products;
	}

	/**
	 * @return product by id
	 */
	public Product getProduct(String pid) {
		String type = validateAndGetType(pid);
		return new Product(pid, type, props.getProperty(pid + ".host"));
	}

	/**
	 * @param pid
	 * @return implementation of adapter by pid
	 */
	public ComputerSystemAdapter getAdapter(String pid) {
		String type = validateAndGetType(pid);
		if (type.equals(AdapterType.KONEAK1000.name())) {
			return new KoneaK1000AdapterImpl(pid, props);
		} else {
			return new K1000AdapterImpl(pid, props);
		}
	}

	private String validateAndGetType(String pid) {
		if (!StringUtils.hasText(props.getProperty(pid + ".type"))) {
			throw new NotFoundException("Product id " + pid
					+ " is not supported");
		}
		return props.getProperty(pid + ".type");
	}
}
