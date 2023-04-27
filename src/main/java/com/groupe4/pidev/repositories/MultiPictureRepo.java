package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.entities.MultiPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MultiPictureRepo extends JpaRepository<MultiPicture,Long> {
    ArrayList<MultiPicture> findByImage(Evenement evenement);
}
