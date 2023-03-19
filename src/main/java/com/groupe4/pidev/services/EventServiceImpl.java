package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.repositories.EventRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventServiceImpl implements IEventService {

    private EventRepo eventRepo;
    @Override
    public Evenement addEvent(Evenement evenement) {
        return eventRepo.save(evenement);
    }

    @Override
    public Evenement editEvent(Evenement evenement) {
        return eventRepo.save(evenement);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }

    @Override
    public List<Evenement> findAllEvent() {
        return eventRepo.findAll();
    }

    @Override
    public Evenement findEventById(Long id) {
        return eventRepo.findById(id).orElse(null);
    }
}
