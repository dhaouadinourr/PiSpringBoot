package com.groupe4.pidev.security.services;

import com.groupe4.pidev.entities.Role;
import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepository;

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user!=null)
        {
            user.setToken(token);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("Could not find user with this email");
        }
    }

    @Override
    public User getByToken(String token) {
        User user = userRepository.findByToken(token);

            return user;
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setToken(null);
        userRepository.save(user);

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
