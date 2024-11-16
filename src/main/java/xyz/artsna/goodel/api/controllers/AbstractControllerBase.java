package xyz.artsna.goodel.api.controllers;

import jakarta.inject.Inject;
import xyz.artsna.goodel.domain.exceptions.ForbiddenException;
import xyz.artsna.goodel.infra.database.repositories.ClientRepository;
import xyz.artsna.goodel.infra.database.repositories.EmployeeRepository;
import xyz.artsna.goodel.infra.database.repositories.UserRepository;
import xyz.artsna.goodel.infra.security.Subject;

import java.security.Principal;

public class AbstractControllerBase {

    @Inject
    ClientRepository clients;

    @Inject
    UserRepository users;

    @Inject
    EmployeeRepository employees;

    protected Subject validateSubject(Principal principal) {
        if(!(principal instanceof Subject subject))
            throw new ForbiddenException("Invalid subject type. Expected Subject but found " + principal.getClass().getName());

        switch (subject.getSubjectType()) {
            case USER -> {
                if (users.count("id", subject.getId()) == 0)
                    throw new ForbiddenException("Invalid subject. The associated entity was not found.");
            }
            case EMPLOYEE -> {
                if (employees.count("id", subject.getId()) == 0)
                    throw new ForbiddenException("Invalid subject. The associated entity was not found.");
            }
            case CLIENT -> {
                if (clients.count("id", subject.getId()) == 0)
                    throw new ForbiddenException("Invalid subject. The associated entity was not found.");
            }
            default -> throw new ForbiddenException("Invalid subject type");
        }

        return subject;
    }
}
