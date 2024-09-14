package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleDao;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Transactional
    public void saveAll(List<Role> roles) {
        roleDao.saveAll(roles);
    }

    @Transactional
    public void deleteRoles(List<Role> roles) {
        roleDao.deleteAll(roles);
    }

    @Transactional
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
