package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.ProduitCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitCategoryRepo extends JpaRepository<ProduitCategory,Long> {
}
