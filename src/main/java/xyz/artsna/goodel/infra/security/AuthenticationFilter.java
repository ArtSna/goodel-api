package xyz.artsna.goodel.infra.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Priority;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import xyz.artsna.goodel.domain.exceptions.UnauthorizedException;
import xyz.artsna.goodel.domain.providers.JwtTokenProvider;

import java.io.IOException;
import java.util.Arrays;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
	JwtTokenProvider tokenProvider;
	@Context
	ResourceInfo resourceInfo;

    @Override
	public void filter(ContainerRequestContext context) throws IOException {
	    String header = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        String AUTH_HEADER_PREFIX = "Token ";
        if (header != null && header.startsWith(AUTH_HEADER_PREFIX)) {
	        try {
				String token = header.replace(AUTH_HEADER_PREFIX, "");

				var subject = tokenProvider.verify(token);

				RolesAllowed roles = (resourceInfo.getResourceMethod().isAnnotationPresent(RolesAllowed.class) ?
						resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class) :
						null
				);
				if(roles == null)
					throw new IllegalArgumentException("No roles specified for the controller");

				if(!Arrays.asList(roles.value()).contains(subject.getSubjectType().toString()))
					throw new UnauthorizedException("Role not allowed");

                context.setSecurityContext(new AppContext(subject));
			} catch (JWTVerificationException e) {
				throw new UnauthorizedException(e.getMessage());
			}
		} else {
	    	context.setSecurityContext(new EmptySecurityContext());
	    }
	}
	
}