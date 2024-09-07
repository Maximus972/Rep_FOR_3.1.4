package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleDao;
import ru.kata.spring.boot_security.demo.repository.UserDao;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userdao, RoleService roleservice, PasswordEncoder passwordEncoder) {
        this.userDao = userdao;
        this.roleService = roleservice;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public void saveUserWithRole(User user) {
        List<Role> roles = user.getRoles().stream().peek(t -> t.setUser(user)).toList();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    @Transactional
    public void encryptExistingPasswords() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            // Если пароль не зашифрован
            if (!user.getPassword().startsWith("$2a$")) { // Проверка на формат BCrypt
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userDao.save(user);
            }
        }
    }
}
