package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Paiement;
import com.groupe4.pidev.repositories.PaiementRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaiementService implements IPaiementService{
    private PaiementRepo paiementRepo;
    @Override
    public Paiement addPaiement(Paiement paiement) { return paiementRepo.save(paiement); }
    @Override
     public Paiement editPaiement(Paiement paiement) { return paiementRepo.save(paiement); }
    @Override
    public void deletePaiement(Long id) {
        paiementRepo.deleteById(id);
    }

    @Override
    public List<Paiement> findAllPaiement() {
        return paiementRepo.findAll();
    }

    @Override
    public Paiement findPaiementById(Long id) {
        return paiementRepo.findById(id).orElse(null);
    }

}
