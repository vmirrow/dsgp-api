package com.dell.dsg.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dell.dsg.adapter.ComputerSystemAdapter;
import com.dell.dsg.adapter.ComputerSystemFactory;
import com.dell.dsg.adapter.domain.Product;
import com.dell.dsg.domain.ComputerSystem;
import com.dell.dsg.domain.ComputerSystemBase;

/**
 * DSG Platform API
 * 
 * @version 0.01
 * @author vtihomir
 * 
 */
@Controller
@Path(DsgpApi.API_URL)
public class DsgpApi {
	public static final String API_URL = "/api";

	@Autowired
	ComputerSystemFactory factory;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("products")
	@Wrapped
	public List<Product> getProducts() {
		return factory.getActiveProducts();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("product/{pid}/computers")
	@Wrapped
	public List<ComputerSystemBase> getComputerSystems(
			@PathParam("pid") String pid) {
		ComputerSystemAdapter adapter = factory.getAdapter(pid);
		return adapter.getComputerSystems();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("product/{pid}/computer/{id}")
	public ComputerSystem getComputerSystems(@PathParam("pid") String pid,
			@PathParam("id") String id) {
		ComputerSystemAdapter adapter = factory.getAdapter(pid);
		return adapter.getComputerById(id);
	}

}
