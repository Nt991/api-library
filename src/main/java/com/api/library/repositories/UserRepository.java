package com.api.library.repositories;

import com.api.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();

    User findById(long id);

    User findByUsername(String username);
}
