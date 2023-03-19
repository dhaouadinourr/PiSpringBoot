package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepo extends JpaRepository<Mission,Long> {
}
