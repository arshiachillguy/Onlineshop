package com.example.demo.repositoriesforusers;

import com.example.demo.Users.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface Usersrepository extends JpaRepository<user, Long> {


    Optional<user> findByUsername(String username);

    Optional<user> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
