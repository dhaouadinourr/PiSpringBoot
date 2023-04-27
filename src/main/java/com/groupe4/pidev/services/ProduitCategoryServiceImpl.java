package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.ProduitCategory;
import com.groupe4.pidev.repositories.ProduitCategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProduitCategoryServiceImpl implements IProduitCategoryservice{
    private ProduitCategoryRepo produitCategoryRepo;
    @Override
    public ProduitCategory addProduit(ProduitCategory Produit) {
        return produitCategoryRepo.save(Produit);
    }

    @Override
    public ProduitCategory editProduit(ProduitCategory Produit) {
        return produitCategoryRepo.save(Produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitCategoryRepo.deleteById(id);
    }

    @Override
    public List<ProduitCategory> findAllProduit() {
        return produitCategoryRepo.findAll();
    }

    @Override
    public ProduitCategory findProduitById(Long id) {
        return produitCategoryRepo.findById(id).orElse(null);
    }
}
