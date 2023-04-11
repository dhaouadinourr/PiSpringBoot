package com.groupe4.pidev.services;


import com.groupe4.pidev.entities.Produit;
import com.groupe4.pidev.entities.ProduitCategory;

import java.util.List;

public interface IProduitService {

    Produit addProduit(Produit Produit);
    Produit editProduit(Produit Produit);
    void deleteProduit(Long id);
    List<Produit> findAllProduit();
    Produit findProduitById(Long id);

    List<Produit>  findProdByCat(ProduitCategory produitCategory);
}

