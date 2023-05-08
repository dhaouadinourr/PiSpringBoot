package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.entities.EventComment;
import com.groupe4.pidev.services.IEventCommentService;
import com.groupe4.pidev.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@RequestMapping("/eventComment")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EventCommentController {

    IEventCommentService iEventCommentService;
    IEventService iEventService;

    @PostMapping("/add")
    public EventComment add(@RequestBody EventComment com) {
        List<String> badWords= Collections.unmodifiableList(Arrays.asList("bob","fuck","shit","sh*t","bitch","bastard","cunt","trash","wanker","nigga","slut","damn","sucker","cracker","poop","boob","buub","f*ck","b*tch"));;
        Evenement p=iEventService.findEventById(com.getEvents().getId_event());

        if(p==null){throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "NULL");}


        if(com.getNomContenu().replaceAll("\\s+","").equals("")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Empty");
        }

        for(String bW : badWords){
            if(com.getNomContenu().toLowerCase().replaceAll("\\s+","").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g").contains(bW)){
                throw new ResponseStatusException( HttpStatus.NOT_ACCEPTABLE, "Bad words");}
        }

        for(EventComment co :p.getEventComments()){
            if(com.getEvents().equals(co.getEvents())){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duplicated");
            }
        }
        return iEventCommentService.addCom(com);
    }

    @PutMapping("/update")
    public EventComment update(@RequestBody EventComment com){
        List<String> badWords= Collections.unmodifiableList(Arrays.asList("bob","fuck","shit","sh*t","bitch","bastard","cunt","trash","wanker","nigga","slut","damn","sucker","cracker","poop","boob","buub","f*ck","b*tch"));;
        Evenement p=iEventService.findEventById(com.getEvents().getId_event());

        if(p==null){throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "NULL");}


        if(com.getNomContenu().replaceAll("\\s+","").equals("")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Empty");
        }

        for(String bW : badWords){
            if(com.getNomContenu().toLowerCase().replaceAll("\\s+","").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g").contains(bW)){
                throw new ResponseStatusException( HttpStatus.NOT_ACCEPTABLE, "Bad words");}
        }

        for(EventComment co :p.getEventComments()){
            if(com.getEvents().equals(co.getEvents())){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duplicated");
            }
        }
        return iEventCommentService.editCom(com);
    }
    @GetMapping("/getAll")
    public List<EventComment> getAll() {
        return iEventCommentService.findAllEventCom();
    }
    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable("id") Long id){iEventCommentService.deleteCom(id);}

    @GetMapping("/get/{id}")
    public EventComment get(@PathVariable("id")Long id){
        return iEventCommentService.findEventComById(id);
    }
}
