package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepo extends JpaRepository <Paiement,Long> {
}
