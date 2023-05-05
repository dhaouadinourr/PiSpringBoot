package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Evenement;

import java.util.List;

public interface IEventService {
    Evenement addEvent(Evenement evenement);
    Evenement editEvent(Evenement evenement);
    void deleteEvent(Long id);
    List<Evenement> findAllEvent();
    Evenement findEventById(Long id);
    public String SendSms(String Phone, String message);

}
