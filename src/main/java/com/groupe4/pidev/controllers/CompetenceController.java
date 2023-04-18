package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.Competence;
import com.groupe4.pidev.services.ICompetenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Competence")
@CrossOrigin(origins = "http://localhost:4200")
public class CompetenceController {
    ICompetenceService CompetenceService;

    public CompetenceController(ICompetenceService CompetenceService) {
        this.CompetenceService = CompetenceService;
    }

    @PostMapping("/add")
    public Competence ajouterCompetence(@RequestBody Competence Competence) {
        return CompetenceService.ajouterCompetence(Competence);
    }
    @PutMapping("/update")
    public Competence modifierCompetence(@RequestBody Competence Competence) {
        return CompetenceService.modifierCompetence(Competence);
    }
    @GetMapping("/getAll")
    public List<Competence> afficherCompetence() {
        return CompetenceService.afficherCompetence();
    }

    @GetMapping("/getById/{id}")
    public Competence afficherCompetence(@PathVariable("id") Long id) {
        return CompetenceService.afficherCompetence(id);
    }
    @DeleteMapping("/delete/{id}")
    public void supprimerCompetence(@PathVariable("id") Long id) {
        CompetenceService.supprimerCompetence(id);
    }

    @GetMapping("getByName")
    public Competence getComByName(@RequestBody String name){
        return CompetenceService.getComByName(name);
    }
}
