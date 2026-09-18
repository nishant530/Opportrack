package com.nishant.opportrack.service;

import com.nishant.opportrack.model.User;
import com.nishant.opportrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        // Password ko plain text mein save nahi karte - encrypt karke save karte hain
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
    return userRepository.findByEmail(email);
    }

public boolean checkPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
}
}