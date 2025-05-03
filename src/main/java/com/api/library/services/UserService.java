package com.api.library.services;

import com.api.library.entities.User;
import com.api.library.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(noRollbackFor = {
            DataIntegrityViolationException.class,
            IllegalArgumentException.class,
            ConstraintViolationException.class
    })
    public User saveUser(User user){
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    /*public User saveUser(User user){
        return userRepository.save(user);
    }*/

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(long userId){
        return userRepository.findById(userId);
    }

    public void deleteById(long userId){
        userRepository.deleteById(userId);
    }
}
