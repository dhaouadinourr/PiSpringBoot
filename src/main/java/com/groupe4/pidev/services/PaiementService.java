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
    //@Override
    //public Paiement addPaiement(Paiement paiement) {
    //   if (paiement.getMontant() == null || paiement.getMontant().isNaN()) {
    //       throw new IllegalArgumentException("Please type a correct value");
    //  }
    //   try {
    //      return paiementRepo.save(paiement);
    //   } catch (Exception e) {
    //        throw new RuntimeException("Failed to add payment", e);}
    //}
    @Override
     public Paiement editPaiement(Paiement paiement) { return paiementRepo.save(paiement); }
    //@Override
    //public Paiement editPaiement(Paiement paiement)  throws RuntimeException {

    //    if (paiement.getId_paiment() == null) {
    //        throw new IllegalArgumentException("Payment ID cannot be null");
    //    }
    //    if (paiement.getMontant() == null || paiement.getMontant().isNaN()) {
    //        throw new IllegalArgumentException("Please type a correct value");
    //    }
    //    try {
    //        return paiementRepo.save(paiement);
    //    } catch (Exception e) {
    //       throw new RuntimeException("Failed to update payment", e);
    //  }
    //}
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
