package ru.kata.spring.boot_security.demo.model;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {}

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String getAuthority() {
        return role;
    }
}
