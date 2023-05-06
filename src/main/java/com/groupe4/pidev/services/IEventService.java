package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.entities.Evenement;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

public interface IEventService {
    long addEvent(Evenement evenement);
    Evenement editEvent(Evenement evenement);
    void deleteEvent(Long id);
    List<Evenement> findAllEvent();
    Evenement findEventById(Long id);
  /*  public String SendSms(String Phone, String message);*/

    public List<Evenement>findEventbyCateg(Categorie cat);
    public List<Evenement> findByDate(Date db , Date df);
    public Long getParticipantCountByEvent(Long eventId);
    public void addUserToEvent(Long eventId, Long userId);
    public void removeUserFromEvent(Long eventId, Long userId);
}
