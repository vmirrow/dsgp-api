package com.dell.dsg.api.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Provider
@Component
public class NullPointerExceptionProvider implements
		ExceptionMapper<NullPointerException> {

	@Override
	public Response toResponse(NullPointerException exception) {
		return Response.status(999).build();
	}

}
