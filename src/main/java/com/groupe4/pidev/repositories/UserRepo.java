package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <User,Long> {
    Optional<User> findByUsername(String username);
    @Query("select us from User us where us.username = :username")
    User getByUsername(@Param("username") String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findUserByEmail(String userEmail);

    User findByToken(String token);
}
