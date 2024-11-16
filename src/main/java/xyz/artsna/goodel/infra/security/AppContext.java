package xyz.artsna.goodel.infra.security;

import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;

public record AppContext(Subject subject) implements SecurityContext {

    @Override
    public Principal getUserPrincipal() {
        return subject;
    }

    @Override
    public boolean isUserInRole(String role) {
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
