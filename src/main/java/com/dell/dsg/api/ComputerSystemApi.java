package com.dell.dsg.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
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
@Path(ComputerSystemApi.API_URL)
// @Produces({ MediaType.APPLICATION_JSON })
@Produces({ "application/dsg.api.v1+json" })
@Consumes({ "application/dsg.api.v1+json" })
public class ComputerSystemApi {
	public static final String API_URL = "/api";

	@Autowired
	ComputerSystemFactory factory;

	@GET
	public String getInfo() throws Exception {
		throw new NotSupportedException();
		// Oreturn "DSG Platform API v0.01";
	}

	@GET
	@Path("products")
	@Wrapped
	public List<Product> getProducts() {
		return factory.getActiveProducts();
	}

	@GET
	@Path("product/{pid}")
	public Product getProduct(@PathParam("pid") String pid) {
		return factory.getProduct(pid);
	}

	@GET
	@Path("product/{pid}/computers")
	@Wrapped
	public List<ComputerSystemBase> getComputerSystems(
			@PathParam("pid") String pid) {
		ComputerSystemAdapter adapter = factory.getAdapter(pid);
		return adapter.getComputerSystems();
	}

	@GET
	@Path("product/{pid}/computer/{id}")
	public ComputerSystem getComputerSystems(@PathParam("pid") String pid,
			@PathParam("id") String id) {
		ComputerSystemAdapter adapter = factory.getAdapter(pid);
		return adapter.getComputerById(id);
	}

	@GET
	@Path("computers")
	@Wrapped
	public List<ComputerSystemBase> searchComputerSystems(
			@QueryParam("key") String key) {
		if (StringUtils.isEmpty(key)) {
			throw new BadRequestException("key parameter is not defined");
		}
		List<ComputerSystemBase> result = new ArrayList<ComputerSystemBase>();
		for (Product product : factory.getActiveProducts()) {
			ComputerSystemAdapter adapter = factory
					.getAdapter(product.getPid());
			result.addAll(adapter.searchComputerSystems(key));
		}
		return result;
	}

}
