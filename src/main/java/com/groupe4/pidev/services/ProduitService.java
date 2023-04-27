package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Produit;
import com.groupe4.pidev.entities.ProduitCategory;
import com.groupe4.pidev.repositories.ProduitRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProduitService implements IProduitService {
    private ProduitRepo produitRepo;


    @Override
    public Produit addProduit(Produit Produit) {
        return produitRepo.save(Produit);
    }

    @Override
    public Produit editProduit(Produit Produit) {
        return produitRepo.save(Produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepo.deleteById(id);

    }

    @Override
    public List<Produit> findAllProduit() {
        return produitRepo.findAll();
    }

    @Override
    public Produit findProduitById(Long id) {
        return produitRepo.findById(id).orElse(null);
    }

    @Override
    public List<Produit> findProdByCat(ProduitCategory produitCategory) {
        return produitRepo.findByProduitCategory(produitCategory);
    }
}