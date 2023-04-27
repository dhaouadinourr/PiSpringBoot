package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement addPaiement(Paiement paiement);

    Paiement editPaiement(Paiement paiement);

    void deletePaiement(Long id);

    List<Paiement> findAllPaiement();

    Paiement findPaiementById(Long id);
}
