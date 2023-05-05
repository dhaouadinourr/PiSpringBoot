package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User,Long> {

}
