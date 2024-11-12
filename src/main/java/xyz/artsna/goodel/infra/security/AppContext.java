package xyz.artsna.goodel.infra.security;

import jakarta.ws.rs.core.SecurityContext;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.security.Principal;

@Getter
public class AppContext implements SecurityContext {

    private final Subject subject;

    public AppContext(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Principal getUserPrincipal() {
        return subject;
    }

    @Override
    public boolean isUserInRole(@NotNull String role) {
        return role.equalsIgnoreCase(subject.getSubjectType().toString());
    }

    public SubjectType getSubjectType() {
        return subject.getSubjectType();
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return "";
    }
}
