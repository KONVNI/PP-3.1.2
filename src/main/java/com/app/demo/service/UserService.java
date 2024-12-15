package com.app.demo.service;

import com.app.demo.model.User;
import com.app.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }

        return user;
    }
    @Transactional
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public User findById(Long id) {
        return userRepository.getOne(id);
    }
    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }
    @Transactional
    public User updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
