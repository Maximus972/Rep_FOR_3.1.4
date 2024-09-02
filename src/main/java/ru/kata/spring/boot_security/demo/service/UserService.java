package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserDao;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
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
