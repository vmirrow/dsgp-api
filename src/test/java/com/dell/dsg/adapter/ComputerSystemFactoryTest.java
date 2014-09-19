package com.dell.dsg.adapter;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dell.dsg.adapter.ComputerSystemFactory;
import com.dell.dsg.adapter.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class ComputerSystemFactoryTest {
	static final Logger logger = Logger
			.getLogger(ComputerSystemFactoryTest.class);

	@Autowired
	ComputerSystemFactory factory;

	@Test
	public void testGetProducts() {
		List<Product> products = factory.getActiveProducts();
		for (Product prod : products) {
			logger.info(prod);
		}
	}

}
