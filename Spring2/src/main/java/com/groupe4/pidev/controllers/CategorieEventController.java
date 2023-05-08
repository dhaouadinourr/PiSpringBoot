package com.groupe4.pidev.controllers;


import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.services.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/categorie")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategorieEventController {
    ICategorieService iCategorieService;

    @PostMapping("/add")
    public Categorie add(@RequestBody Categorie c) {
        return iCategorieService.addCateg(c);
    }

    @PutMapping("/update")
    public Categorie update(@RequestBody Categorie c) {
        return iCategorieService.editCateg(c);
    }

    @GetMapping("/getAll")
    public List<Categorie> getAll() {
        return iCategorieService.findAllCateg();

    }
    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable("id") Long id){iCategorieService.deleteCateg(id);}

    @GetMapping("/get/{id}")
    public Categorie get(@PathVariable("id")Long id){
        return iCategorieService.findCategById(id);
    }
}
