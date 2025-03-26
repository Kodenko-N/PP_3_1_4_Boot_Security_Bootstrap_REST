package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.Set;

public interface RoleService {
    public Set<Role> getAllRoles();

    public Role getRoleById(int id);

    public void save(Role role);

    public void update(Role role);

    public void delete(int id);
}
