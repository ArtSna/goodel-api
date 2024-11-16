package xyz.artsna.goodel.infra.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.enums.EmployeeFunction;

import java.security.Principal;
import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Subject implements Principal {

    private UUID id;
    private String first_name, last_name;
    private String email;

    private String phone;
    private EmployeeFunction function;

    private SubjectType subjectType;

    @Override
    public String getName() {
        return first_name;
    }

    public String getFullName() {
        return String.format("%s %s", first_name, last_name);
    }

}
