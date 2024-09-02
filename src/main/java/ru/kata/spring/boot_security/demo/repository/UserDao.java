package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Override
    default User save(User user) {

        return saveAndFlush(user);
    };

    default User saveUser(User user) {

        save(user);
        return user;
    }
}
