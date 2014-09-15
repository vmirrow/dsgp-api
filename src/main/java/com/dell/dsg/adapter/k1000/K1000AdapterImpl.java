package com.dell.dsg.adapter.k1000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.http.HttpStatus;

import com.dell.dsg.adapter.ComputerSystemAdapter;
import com.dell.dsg.adapter.domain.Login;
import com.dell.dsg.domain.ComputerSystem;
import com.dell.dsg.domain.ComputerSystemBase;

/**
 * Implementation of K1000 adapter
 * 
 * @author vtihomir
 */
public class K1000AdapterImpl implements ComputerSystemAdapter {
	static final Logger logger = Logger.getLogger(K1000AdapterImpl.class);

	private String host;
	private String user;
	private String password;
	private String pid;

	public K1000AdapterImpl(String pid, Properties props) {
		super();
		this.pid = pid;
		host = props.getProperty(pid + ".host");
		user = props.getProperty(pid + ".user");
		password = props.getProperty(pid + ".password");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComputerSystemBase> getComputerSystems() {
		Response response = getTarget().path("machines").request()
				.accept(MediaType.APPLICATION_JSON_TYPE).get();
		if (response.getStatus() != HttpStatus.OK.value()) {
			logger.error(response.getStatusInfo());
			throw new NotFoundException();
		}
		// TODO pojo for k1000?
		Collection<Map> machines = response.readEntity(Collection.class);
		List<ComputerSystemBase> computers = new ArrayList<ComputerSystemBase>(); 
		for (Map machine : machines) {
			ComputerSystemBase comp = new ComputerSystemBase(pid);
			comp.mapK1000(machine);
			computers.add(comp);
		}
		response.close();
		return computers;
	}

	public ComputerSystem getComputerById(String id) {
		Response response = getTarget().path("machine/" + id).request()
				.accept(MediaType.APPLICATION_JSON_TYPE).get();
		Map machine = response.readEntity(Map.class);
		if (response.getStatus() != HttpStatus.OK.value()) {
			logger.error(response.getStatusInfo());
			throw new NotFoundException();
		}
		ComputerSystem computer = new ComputerSystem(pid);
		computer.mapK1000(machine);
		response.close();
		return computer;
	}
	
	private ResteasyWebTarget getTarget() {
		// TODO don't create in every call
		// TODO auth support
		ResteasyClient client = new ResteasyClientBuilder().build();
		Login login = new Login(user, password);
		ResteasyWebTarget target = client.target(host);
		Response response = target.path("login").request()
				.header(HttpHeaders.USER_AGENT, "DSGP")
				.post(Entity.entity(login, MediaType.APPLICATION_JSON_TYPE));
		if (Response.Status.OK.getStatusCode() != response.getStatus()) {
			logger.error("Unable to login to " + host);
		}
		response.close();
		return target;
	}


}
