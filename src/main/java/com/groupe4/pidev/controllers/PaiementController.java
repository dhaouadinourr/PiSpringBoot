package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.Paiement;
import com.groupe4.pidev.services.IPaiementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("paiement")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PaiementController {
    private IPaiementService iPaiementService;

    @PostMapping("/add")
    public Paiement addPaiement(@RequestBody Paiement Paiement) {
        return iPaiementService.addPaiement(Paiement);
    }

    @PutMapping("/update")
    public Paiement editPaiement(@RequestBody Paiement Paiement) {
        return iPaiementService.editPaiement(Paiement);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePaiement(@PathVariable Long id) {
        iPaiementService.deletePaiement(id);

    }

    @GetMapping("/getAll")
    public List<Paiement> findAllPaiement() {
        return iPaiementService.findAllPaiement();
    }

    @GetMapping("/get/{id}")
    public Paiement findPaiementById(@PathVariable Long id) {
        return iPaiementService.findPaiementById(id);
    }



    }

