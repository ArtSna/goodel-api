package xyz.artsna.goodel.infra.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import org.jetbrains.annotations.NotNull;
import xyz.artsna.goodel.domain.providers.JwtTokenProvider;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
	JwtTokenProvider tokenProvider;

    @Override
	public void filter(@NotNull ContainerRequestContext context) {
	    String header = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        String AUTH_HEADER_PREFIX = "Token ";
        if (header != null && header.startsWith(AUTH_HEADER_PREFIX)) {
	        String token = header.replace(AUTH_HEADER_PREFIX, "");
			context.setSecurityContext(new AppContext(tokenProvider.verify(token)));
		} else {
	    	context.setSecurityContext(new EmptySecurityContext());
	    }
	}
	
}