package com.dell.dsg.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dell.dsg.adapter.ComputerSystemAdapter;
import com.dell.dsg.adapter.ComputerSystemFactory;
import com.dell.dsg.domain.ComputerSystem;

/**
 * DSG Platform API
 * 
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
	@Path("{pid}/computers")
	@Wrapped
	public Response getComputerSystems(@PathParam("pid") String pid) {
		ComputerSystemAdapter adapter = factory.getAdapter(pid);
		return Response.status(200).entity(adapter.getComputerSystems())
				.build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{pid}/computer/{id}")
	public ComputerSystem getComputerSystems(@PathParam("pid") String pid,
			@PathParam("id") String id) {
		ComputerSystemAdapter adapter = factory.getAdapter(pid);
		return adapter.getComputerById(id);
	}

}
