package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.repositories.CategorieRepo;
import com.groupe4.pidev.repositories.EventRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategorieService implements ICategorieService{
    private CategorieRepo categorieRepo;
    @Override
    public Categorie addCateg(Categorie categorie) {
        return categorieRepo.save(categorie);
    }

    @Override
    public Categorie editCateg(Categorie categorie) {
        return categorieRepo.save(categorie);
    }

    @Override
    public void deleteCateg(Long id) {
        categorieRepo.deleteById(id);

    }

    @Override
    public List<Categorie> findAllCateg() {
        return categorieRepo.findAll();
    }

    @Override
    public Categorie findCategById(Long id) {
        return categorieRepo.findById(id).orElse(null);
    }
}
