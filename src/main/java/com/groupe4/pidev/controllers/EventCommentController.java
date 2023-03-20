package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.EventComment;
import com.groupe4.pidev.services.IEventCommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/eventComment")
@RestController
@AllArgsConstructor
public class EventCommentController {

    IEventCommentService iEventCommentService;

    @PostMapping("/add")
    public EventComment add(@RequestBody EventComment com) {
        return iEventCommentService.addCom(com);
    }

    @PutMapping("/update")
    public EventComment update(@RequestBody EventComment com){
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
