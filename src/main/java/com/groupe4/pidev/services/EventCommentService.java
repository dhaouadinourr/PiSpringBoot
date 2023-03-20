package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.EventComment;
import com.groupe4.pidev.repositories.EventCommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class EventCommentService implements IEventCommentService {
        private EventCommentRepo eventCommentRepo;
    @Override
    public EventComment addCom(EventComment eventComment) {
        return eventCommentRepo.save(eventComment);
    }

    @Override
    public EventComment editCom(EventComment eventComment) {
        return eventCommentRepo.save(eventComment);
    }

    @Override
    public void deleteCom(Long id) {
        eventCommentRepo.deleteById(id);

    }

    @Override
    public List<EventComment> findAllEventCom() {
        return eventCommentRepo.findAll();
    }

    @Override
    public EventComment findEventComById(Long id) {
        return eventCommentRepo.findById(id).orElse(null);
    }
}
