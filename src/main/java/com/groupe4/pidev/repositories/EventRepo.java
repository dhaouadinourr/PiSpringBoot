package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Evenement,Long> {
    public List<Evenement> findByCateg(Categorie category);
}
