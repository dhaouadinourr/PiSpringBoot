package com.groupe4.pidev.services;

import com.groupe4.pidev.Exception.OutOfNumberException;
import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.entities.Mymission;
import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.repositories.EventRepo;
import com.groupe4.pidev.repositories.UserRepo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements IEventService {


    private final EventRepo eventRepo;
    private final UserRepo userRepo;

    /* @Value("${app.TWILIO_AUTH_TOKEN}")
     private  String Service_TWILIO_AUTH_TOKEN;
     @Value("${app.TWILIO_ACCOUNT_SID}")
     private  String Service_TWILIO_ACCOUNT_SID;*/
    @Override
    public long addEvent(Evenement evenement) {
        return eventRepo.save(evenement).getId_event();
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
        List<Evenement> c = eventRepo.findAll();
        System.out.println(c);
        return c;

    }

    @Override
    public Evenement findEventById(Long id) {
        return eventRepo.findById(id).orElse(null);
    }

    @Async
    /* @Override*/
   /* public String SendSms(String Phone, String message){
        Twilio.init(Service_TWILIO_ACCOUNT_SID, Service_TWILIO_AUTH_TOKEN);
        Message.creator(new PhoneNumber(Phone),
                new PhoneNumber("+15074605523"), message).create();
        log.info("Sms Send");
        return "Message sent successfully";
    }*/

    @Override
    public List<Evenement> findEventbyCateg(Categorie cat) {
        return eventRepo.findByCateg(cat);
    }

    @Override
    public List<Evenement> findByDate(Date db, Date df) {
        return eventRepo.findByDateDebutBetween(db, df);
    }

    public Long getParticipantCountByEvent(Long eventId) {
        return eventRepo.getParticipantCountByEvent(eventId);
    }

    @Override
    public void addUserToEvent(Long eventId, Long userId) {
        Evenement event = eventRepo.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + eventId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
        event.addParticipant(user);
        eventRepo.save(event);
    }

    public void removeUserFromEvent(Long eventId, Long userId) {
        Evenement event = eventRepo.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + eventId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
        event.removeParticipant(user);
        eventRepo.save(event);
    }

    @Override
    @Transactional
    public Evenement AssignUserToEvent(Long eventId, String nameU) {

        Evenement event = eventRepo.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + eventId));
        User user = userRepo.getByUsername(nameU);

        event.addParticipant(user);
        return eventRepo.save(event);


    }
}

