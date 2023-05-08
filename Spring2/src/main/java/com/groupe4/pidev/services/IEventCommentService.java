package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.entities.EventComment;

import java.util.List;

public interface IEventCommentService {
    EventComment addCom(EventComment eventComment);
    EventComment editCom(EventComment eventComment);
    void deleteCom(Long id);
    List<EventComment> findAllEventCom();
    EventComment findEventComById(Long id);
    //

}
