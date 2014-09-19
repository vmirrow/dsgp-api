package com.dell.dsg.api.provider;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Provider
public class RequestInterceptor implements ContainerRequestFilter {
	static final Logger logger = Logger.getLogger(RequestInterceptor.class);

	@Override
    public void filter(ContainerRequestContext requestContext){
		String type = requestContext.getHeaderString("Content-Type");
		logger.debug("Content-Type:" + type);
    }
	
}
