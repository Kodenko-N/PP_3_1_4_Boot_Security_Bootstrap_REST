package ru.kata.spring.boot_security.demo.service;

import org.springframework.core.convert.converter.Converter;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.Set;

public interface RoleService extends Converter<String, Role> {
    Set<Role> getAllRoles();

    Role getRoleById(int id);

    void save(Role role);

    void update(Role role);

    void delete(int id);
}
