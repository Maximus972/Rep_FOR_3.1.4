package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
//    default User saveUser(User user) {
//        save(user);
//        return user;
//    }

//    default User findUserByUsername(String username) {
//
//    }
}
