package com.groupe4.pidev.controllers;


import com.groupe4.pidev.entities.Produit;
import com.groupe4.pidev.entities.ProduitCategory;
import com.groupe4.pidev.services.IProduitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("produit")
@AllArgsConstructor
public class ProduitController {
    private IProduitService iProduitService;

    @PostMapping("/add")
    public Produit addProduit(@RequestBody Produit Produit) {
        return iProduitService.addProduit(Produit);
    }

    @PutMapping("/update")
    public Produit editProduit(@RequestBody Produit Produit) {
        return iProduitService.editProduit(Produit);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduit(@PathVariable Long id) {
        iProduitService.deleteProduit(id);

    }

    @GetMapping("/getAll")
    public List<Produit> findAllProduit() {
        return iProduitService.findAllProduit();
    }

    @GetMapping("/get/{id}")
    public Produit findProduitById(@PathVariable Long id) {
        return iProduitService.findProduitById(id);
    }

    @GetMapping("/get/{produitCategory}")
    public List<Produit> findProdByCat(@PathVariable ProduitCategory produitCategory) {
        return iProduitService.findProdByCat(produitCategory);
    }
}

