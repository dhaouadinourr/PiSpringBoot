package com.groupe4.pidev.controllers;


import com.groupe4.pidev.entities.ProduitCategory;

import com.groupe4.pidev.services.IProduitCategoryservice;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("produitCategorie")
public class CategorieProduitController {
    IProduitCategoryservice iCategorieService;

    @PostMapping("/add")
    public ProduitCategory add(@RequestBody ProduitCategory c) {
        return iCategorieService.addProduit(c);
    }

    @PutMapping("/update")
    public ProduitCategory update(@RequestBody ProduitCategory c) {
        return iCategorieService.editProduit(c);
    }

    @GetMapping("/getAll")
    public List<ProduitCategory> getAll() {
        return iCategorieService.findAllProduit();

    }
    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable("id") Long id){iCategorieService.deleteProduit(id);}

    @GetMapping("/get/{id}")
    public ProduitCategory get(@PathVariable("id")Long id){
        return iCategorieService.findProduitById(id);
    }
}
