package ru.kata.spring.boot_security.demo.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    @Transactional
    public void delete(int id) {
        roleDao.delete(id);
    }

    @Override // Converter interface method
    public Role convert(String id) {
        return getRoleById(Integer.parseInt(id));
    }
}
