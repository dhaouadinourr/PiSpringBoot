package com.groupe4.pidev.repositories;



import com.groupe4.pidev.entities.Mymission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MymissionRepository extends JpaRepository<Mymission, Long> {

    @Query("select count(ms) from Mymission ms join ms.users where ms.id= :id")
    Long getNbUsers(Long id);


}
