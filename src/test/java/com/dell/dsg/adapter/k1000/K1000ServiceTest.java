package com.dell.dsg.adapter.k1000;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dell.dsg.adapter.ComputerSystemAdapter;
import com.dell.dsg.adapter.ComputerSystemFactory;
import com.dell.dsg.domain.ComputerSystem;
import com.dell.dsg.domain.ComputerSystemBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springmvc-servlet.xml" })
public class K1000ServiceTest {
	static final Logger logger = Logger.getLogger(K1000ServiceTest.class);

	@Autowired
	ComputerSystemFactory factory;

	@Test
	public void testGetComputerSystems() {
		ComputerSystemAdapter adapter = factory.getAdapter("k1d1");
		List<ComputerSystemBase> computers = adapter.getComputerSystems();
		for (ComputerSystemBase comp : computers) {
			logger.info(comp);
		}
	}

	@Test
	public void testGetComputerSystem() {
		ComputerSystemAdapter adapter = factory.getAdapter("k1d1");
		ComputerSystem computer = adapter.getComputerById("200");
		logger.info(computer);
	}

}