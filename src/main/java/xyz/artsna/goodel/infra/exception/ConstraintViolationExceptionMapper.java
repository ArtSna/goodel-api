package xyz.artsna.goodel.infra.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException e) {
		ExceptionResponse res = new ExceptionResponse();

		e.getConstraintViolations().iterator().forEachRemaining(constraint -> {
			res.setMessage(constraint.getMessage());
		});

		return Response.ok(res).status(422).build();
	}
}