package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.entities.Evenement;

import java.util.List;

public interface ICategorieService {
    Categorie addCateg(Categorie categorie);
    Categorie editCateg(Categorie categorie);
    void deleteCateg(Long id);
    List<Categorie> findAllCateg();
    Categorie findCategById(Long id);
}
