package com.groupe4.pidev.security.services;

import com.groupe4.pidev.entities.Role;
import com.groupe4.pidev.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();

    void updateResetPasswordToken(String token,String email);

    User getByToken(String token);

    void updatePassword(User user,String newPassword);

    User findById (Long id);
}
