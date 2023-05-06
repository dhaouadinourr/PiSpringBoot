package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Evenement,Long> {

    public List<Evenement> findByCateg(Categorie category);


    List<Evenement> findByDateDebutBetween(Date db , Date df);

    @Query("SELECT COUNT(p) FROM Evenement e JOIN e.userevent p WHERE e.id_event = :eventId")
    Long getParticipantCountByEvent(@Param("eventId") Long eventId);

}
