package com.dell.dsg.adapter.konea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.http.HttpStatus;

import com.dell.dsg.adapter.ComputerSystemAdapter;
import com.dell.dsg.domain.ComputerSystem;
import com.dell.dsg.domain.ComputerSystemBase;

/**
 * Implementation of K1000 adapter
 * 
 * @author vtihomir
 */
public class KoneaK1000AdapterImpl implements ComputerSystemAdapter {
	static final Logger logger = Logger.getLogger(KoneaK1000AdapterImpl.class);

	private Properties config;
	private String host;
	private String user;
	private String password;
	private String pid;
	private String koneaServer;
	private String koneaAgent;

	public KoneaK1000AdapterImpl(String pid, Properties props) {
		super();
		this.pid = pid;
		host = props.getProperty(pid + ".host");
		user = props.getProperty(pid + ".user");
		password = props.getProperty(pid + ".password");
		koneaServer = props.getProperty(pid + ".konea.server");
		koneaAgent = props.getProperty(pid + ".konea.agent");
		config = props;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComputerSystemBase> getComputerSystems() {
		login();
		String curl = String.format(config.getProperty("konea.k1000.machines"),
				host);
		String response = run(curl);
		Collection<Map> machines = convert(response, Collection.class);
		List<ComputerSystemBase> computers = new ArrayList<ComputerSystemBase>();
		for (Map machine : machines) {
			ComputerSystemBase comp = new ComputerSystemBase(pid);
			comp.mapK1000(machine);
			computers.add(comp);
		}
		return computers;
	}

	@SuppressWarnings("rawtypes")
	public ComputerSystem getComputerById(String id) {
		login();
		String curl = String.format(
				config.getProperty("konea.k1000.machine.id"), host, id);
		String response = run(curl);
		Map machine = convert(response, Map.class);
		ComputerSystem computer = new ComputerSystem(pid);
		computer.mapK1000(machine);
		return computer;
	}

	public List<ComputerSystemBase> searchComputerSystems(String key) {
		List<ComputerSystemBase> result = new ArrayList<ComputerSystemBase>();
		List<ComputerSystemBase> computers = getComputerSystems();
		for (ComputerSystemBase comp : computers) {
			if (comp.toString().contains(key)) {
				result.add(comp);
			}
		}
		return result;
	}

	private void login() {
		String curl = String.format(config.getProperty("konea.k1000.login"),
				user, password, host);
		String result = run(curl);
		logger.debug(result);
	}

	private String run(String command) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(koneaServer + "/agents/"
				+ koneaAgent);
		Response response = target.path("exec").request()
				.post(Entity.text(command));
		String result = response.readEntity(String.class);
		response.close();
		return result;
	}

	private <T> T convert(String response, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		T result = null;
		try {
			result = (T) mapper.readValue(response, type);
		} catch (Exception e) {
			// TODO exception handling
			logger.error(e);

		}
		if (result == null) {
			throw new NotFoundException();
		}
		return result;
	}

}
