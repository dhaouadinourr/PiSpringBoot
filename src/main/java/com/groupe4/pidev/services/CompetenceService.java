package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Competence;
import com.groupe4.pidev.repositories.CompetenceRepository;
import org.springframework.stereotype.Service;
import com.groupe4.pidev.repositories.CompetenceRepository;


import java.util.List;
@Service
public class CompetenceService implements ICompetenceService {
    CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository CompetenceRepository) {
        this.competenceRepository = CompetenceRepository;
    }

    @Override
    public Competence ajouterCompetence(Competence Competence) {
        return competenceRepository.save(Competence);
    }

    @Override
    public Competence modifierCompetence(Competence Competence) {
        return competenceRepository.save(Competence);
    }

    @Override
    public List<Competence> afficherCompetence() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence afficherCompetence(Long id) {
        return competenceRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerCompetence(Long id) {
        competenceRepository.deleteById(id);

    }

    @Override
    public Competence getComByName(String name) {
        return competenceRepository.findCompetenceByCname(name);
    }
}