package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RolesDaoImp;
import ru.kata.spring.boot_security.demo.model.Role;

@Component
    public class StringToRoleConverter implements Converter<String, Role> {

        @Autowired
        private RolesDaoImp roleService;

        @Override
        public Role convert(String id) {
            return roleService.getRoleById(Integer.parseInt(id));
        }
    }

