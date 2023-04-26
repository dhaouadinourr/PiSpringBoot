package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.User;

public interface IUserService {
    User findById (Long id);
}
