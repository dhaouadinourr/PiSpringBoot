package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.entities.EventComment;
import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.security.services.UserServiceImpl;
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
@CrossOrigin(origins = "*")
public class EventCommentController {

    IEventCommentService commentaireService;
    IEventService iEventService;

    UserServiceImpl userService;



    @GetMapping("/retrieve-all-commentaires")
    @ResponseBody
    public List<EventComment> getCommentaire() {
        List<EventComment> listCommentaires = commentaireService.findAllEventCom();
        return listCommentaires;
    }

    @GetMapping(path="/getUser/{id}")
    public User getUSerCommentaire(@PathVariable("id") Long id) throws Exception {
        User u = userService.findById(commentaireService.findEventComById(id).getIdClient());
        return u;
    }

    // http://localhost:8081/add-commentaire
    @PostMapping("/add-commentaire")
    @ResponseBody
    public EventComment addCommentaire(@RequestBody EventComment c) throws Exception{
        List<String> badWords= Collections.unmodifiableList(Arrays.asList("bob","fuck","shit","dick","sh*t","ass","bitch","bastard","cunt","trash","wanker","piss","pussy","twat","crap","arsehole","gash","prick","cock","minge","nigga","slut","damn","sucker","cracker","poop","puup","boob","buub","f*ck","b*tch","3asba","nayek","nikomok","9a7ba","zebi","sorm"));
        Evenement p=iEventService.findEventById(c.getEvents().getId_event());

        if(p==null){throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "NULL");}


        if(c.getComment().replaceAll("\\s+","").equals("")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Empty");
        }

        for(String bW : badWords){
            if(c.getComment().toLowerCase().replaceAll("\\s+","").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g").contains(bW)){
                throw new ResponseStatusException( HttpStatus.NOT_ACCEPTABLE, "Bad Boy");}
        }

        for(EventComment com :p.getComments()){
            if(c.getComment().equals(com.getComment())){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duplicated");
            }
        }



        return commentaireService.addCom(c);
    }

    // http://localhost:8081/retrieve-commentaire/2
    @GetMapping("/retrieve-commentaire/{commentaire-id}")
    @ResponseBody
    public EventComment retrieveCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        return commentaireService.findEventComById(commentaireId);
    }


    // http://localhost:8081/remove-commentaire/{commentaire-id}
    @DeleteMapping("/remove-client/{commentaire-id}")
    @ResponseBody
    public void removeCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        commentaireService.deleteCom(commentaireId);
    }

    // http://localhost:8081/modify-commentaire
    @PutMapping("/modify-commentaire")
    @ResponseBody
    public EventComment modifyClient(@RequestBody EventComment c) throws Exception {
        List<String> badWords=Collections.unmodifiableList(Arrays.asList("bob","fuck","shit","dick","sh*t","ass","bitch","bastard","cunt","trash","wanker","piss","pussy","twat","crap","arsehole","gash","prick","cock","minge","nigga","slut","damn","sucker","cracker","poop","puup","boob","buub","f*ck","b*tch"));
        Evenement p=iEventService.findEventById(c.getEvents().getId_event());

        if(p==null){throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "NULL");}


        if(c.getComment().replaceAll("\\s+","").equals("")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Empty");
        }

        for(String bW : badWords){
            if(c.getComment().toLowerCase().replaceAll("\\s+","").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g").contains(bW)){throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Bad Boy");}
        }

        for(EventComment com :p.getComments()){
            if(c.getComment().equals(com.getComment()) && c.getLikes()==com.getLikes()){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duplicated");
            }
        }
        return commentaireService.editCom(c);
    }
}
