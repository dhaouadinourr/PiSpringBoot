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
        iEventService.SendSms("+21628736139",
                "We are excited to invite you to an upcoming event that we believe you will enjoy. The event will be taking place on [Date] at [Time] and will be located at [Location].\n" +
                        "\n" +
                        "We have added you to the guest list, and we cannot wait for you to join us. We believe that this event will be a great opportunity for you to meet new people, learn new things, and have a great time.\n" +
                        "\n" +
                        "If you have any questions about the event or need any assistance, please do not hesitate to contact us. We look forward to seeing you at the event.\n" +
                        "\n" +
                        "Best regards,");

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
