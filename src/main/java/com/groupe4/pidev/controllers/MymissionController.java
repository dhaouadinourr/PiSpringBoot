package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.Mymission;
import com.groupe4.pidev.services.IMymissionService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("Mymission")
@CrossOrigin(origins = "http://localhost:4200")
public class MymissionController {
     IMymissionService MymissionService;

    public MymissionController(IMymissionService IMymissionService) {
        this.MymissionService = IMymissionService;
    }

    @PostMapping("/add")
    public Mymission ajouterMymission(@RequestBody Mymission Mymission) {
        return MymissionService.ajouterMymission(Mymission);
    }
    @PutMapping("/update")
    public Mymission modifierMymission(@RequestBody Mymission Mymission) {
        return MymissionService.modifierMymission(Mymission);
    }
    @GetMapping("/getAll")
    public List<Mymission> afficherMymission() {
        return MymissionService.afficherMymission();
    }

    @GetMapping("/getById/{id}")
    public Mymission afficherMymission(@PathVariable("id") Long id) {
        return MymissionService.afficherMymission(id);
    }

    @DeleteMapping("/delete/{id}")
    public void supprimerMymission(@PathVariable("id") Long id) {
        MymissionService.supprimerMymission(id);
    }

    @PutMapping("/addWC/{idM}")
    public Mymission addMissionWithCompetence(@PathVariable("idM") Long idMission , @RequestBody Set<Long> idCpmts){
        return MymissionService.addMissionWithCompetence(idMission,idCpmts);
    }

    @PutMapping("addWtUser/{id}/{name}")
    public Mymission AssignUserToMission(@PathVariable("id") Long idMission,@PathVariable("name") String nameU){
        return MymissionService.AssignUserToMission(idMission,nameU);
    }
    @GetMapping("getCapacity/{id}")
    public boolean verifMissionCapacity(@PathVariable("id") Long idMission){
        return MymissionService.verifMissionCapacity(idMission);
    }
    

}
