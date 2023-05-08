package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.ImageFile;
import com.groupe4.pidev.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ImagesRepo extends JpaRepository<ImageFile,Long> {
        ArrayList<ImageFile> findByImage(Produit produit);}
