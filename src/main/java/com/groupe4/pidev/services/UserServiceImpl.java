package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    UserRepo userRepo;


    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
