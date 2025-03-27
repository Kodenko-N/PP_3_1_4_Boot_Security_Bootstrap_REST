package ru.kata.spring.boot_security.demo.service;

import org.springframework.core.convert.converter.Converter;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.Set;

public interface RoleService extends Converter<String, Role> {
    public Set<Role> getAllRoles();

    public Role getRoleById(int id);

    public void save(Role role);

    public void update(Role role);

    public void delete(int id);
}
