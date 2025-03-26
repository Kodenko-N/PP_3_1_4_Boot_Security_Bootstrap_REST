package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.Set;

public interface RoleDao {
    public Set<Role> getAllRoles();
    public Role getRoleById(int id);
    public void save(Role role);
    public void update(Role role);
    public void delete(int id);

}
