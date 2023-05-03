package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.ERole;
import com.groupe4.pidev.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Set<Role> findByName(ERole name);

    @Query("select rl from User us join us.role rl where rl.name=:name")
    Set<Role> getBynaame(@Param("name") ERole name);
}
