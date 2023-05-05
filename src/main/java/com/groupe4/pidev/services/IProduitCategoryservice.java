package com.groupe4.pidev.services;
import com.groupe4.pidev.entities.ProduitCategory;

import java.util.List;

public interface IProduitCategoryservice {
    ProduitCategory addProduit(ProduitCategory Produit);
    ProduitCategory editProduit(ProduitCategory Produit);
    void deleteProduit(Long id);
    List<ProduitCategory> findAllProduit();
    ProduitCategory findProduitById(Long id);
}
