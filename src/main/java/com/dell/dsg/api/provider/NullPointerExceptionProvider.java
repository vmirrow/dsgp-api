package com.dell.dsg.api.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.http.ProtocolException;
import org.springframework.stereotype.Component;

@Provider
@Component
public class NullPointerExceptionProvider implements
		ExceptionMapper<ProtocolException> {

	@Override
	public Response toResponse(ProtocolException exception) {
		return Response.status(999).build();
	}

}
