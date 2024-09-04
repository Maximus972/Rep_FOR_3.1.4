package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    default List<Role> findByIds(List<Long> ids) {
        List<Role> roles = findAll();
        List<Role> filteredRoles = new ArrayList<>();
        for (Role role : roles) {
            if (ids.contains(role.getId())) {
                filteredRoles.add(role);
            }
        }
        return filteredRoles;
    }
}
