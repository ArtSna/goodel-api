package xyz.artsna.goodel.infra.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jetbrains.annotations.NotNull;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(@NotNull Exception e) {
		ExceptionResponse res = new ExceptionResponse();

		res.setMessage(e.getMessage());

		e.printStackTrace();

		return Response.ok(res).status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}