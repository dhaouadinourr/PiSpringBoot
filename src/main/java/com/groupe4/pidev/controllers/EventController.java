package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {
    IEventService iEventService;

    @PostMapping("/add")
    public Evenement add(@RequestBody Evenement e) {
        return iEventService.addEvent(e);
    }

    @PutMapping ("/update")
    public Evenement update(@RequestBody Evenement e){
        return iEventService.editEvent(e);
    }
    @GetMapping ("/getAll")
    public List<Evenement> getAll() {
    return iEventService.findAllEvent();
    }
    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable("id") Long id){iEventService.deleteEvent(id);}

    @GetMapping("/get/{id}")
    public Evenement get(@PathVariable("id")Long id){
        return iEventService.findEventById(id);
    }
    //
}
