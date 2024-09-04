package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleDao;
import ru.kata.spring.boot_security.demo.repository.UserDao;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public void saveUserWithRole(User user) {
//        List<Role> roles = user.getRoles().forEach(t -> t.setUser(user));
//        for (Role role : roles) {
//            role.setUser(user);
//        }
        List<Role> roles = user.getRoles().stream().peek(t -> t.setUser(user)).toList();
        userDao.saveAndFlush(user);
        roleService.saveAll(roles);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.getById(id);
    }

    @Transactional
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
