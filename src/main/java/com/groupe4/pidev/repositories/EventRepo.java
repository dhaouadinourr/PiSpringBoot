package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Evenement,Long> {

}
