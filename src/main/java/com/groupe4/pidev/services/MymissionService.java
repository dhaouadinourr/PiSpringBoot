package com.groupe4.pidev.services;

import com.groupe4.pidev.Exception.OutOfNumberException;
import com.groupe4.pidev.entities.Competence;
import com.groupe4.pidev.entities.Mymission;
import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.repositories.CompetenceRepository;
import com.groupe4.pidev.repositories.MymissionRepository;
import com.groupe4.pidev.repositories.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MymissionService implements IMymissionService {
    MymissionRepository MymissionRepository;

    UserRepo userRepository;
    private final CompetenceRepository competenceRepository;

    public MymissionService(MymissionRepository MymissionRepository , UserRepo userRepository,
                            CompetenceRepository competenceRepository) {
        this.MymissionRepository = MymissionRepository;
        this.userRepository = userRepository;
        this.competenceRepository = competenceRepository;
    }

    @Override
    public Mymission ajouterMymission(Mymission Mymission) {
        return MymissionRepository.save(Mymission);
    }

    @Override
    public Mymission modifierMymission(Mymission Mymission) {
        return MymissionRepository.save(Mymission);
    }

    @Override
    public List<Mymission> afficherMymission() {
        return MymissionRepository.findAll();
    }

    @Override
    public Mymission afficherMymission(Long id) {
        return MymissionRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerMymission(Long id) {
        MymissionRepository.deleteById(id);
    }

    @Override
    public Mymission addMissionWithCompetence(Long idMission,Set<Long> idCompts ) {
        Mymission mymission = MymissionRepository.findById(idMission).orElse(null);
        Set<Competence> competenceList = new HashSet<>();
        for (Long idCompetence:idCompts){
            Competence competence=competenceRepository.findById(idCompetence).orElse(null);
            competenceList.add(competence);
            mymission.setCompetences(competenceList);
            competenceRepository.save(competence);
        }
        MymissionRepository.save(mymission);
        return mymission;
    }

    @Override
    @Transactional
    public Mymission AssignUserToMission(Long idMission, String nameU) {
        Mymission mymission = MymissionRepository.findById(idMission).orElse(null);

        User user = userRepository.getByUsername(nameU);
        Long nbPlacesMission = MymissionRepository.getNbUsers(idMission);
        if(mymission.getFreePlaces()==null){
            mymission.setFreePlaces(0L);
        }
        if(mymission.getNbPlaces() > nbPlacesMission ) {
            Long nbPlacesUsed = mymission.getNbPlaces();
            Long freeSpace = nbPlacesUsed-nbPlacesMission;
            mymission.setFreePlaces(freeSpace-1);
            mymission.getUsers().add(user);
            MymissionRepository.save(mymission);
        }else if (mymission.getNbPlaces() == nbPlacesMission){
            throw new OutOfNumberException("Mission " + mymission.getId() + " is full");
        }
        return mymission;
    }

    @Override
    public boolean verifMissionCapacity(Long idMission) {
        Mymission mymission = MymissionRepository.findById(idMission).orElse(null);
        Long nbPlacesMission = MymissionRepository.getNbUsers(idMission);
        if (mymission.getNbPlaces() > nbPlacesMission){
            return true;
        }else if (mymission.getNbPlaces() == nbPlacesMission){
            return false;
        }else
            return false;
    }

    @Override
    public Long getNbPlaces(Long idMission) {
        return MymissionRepository.getNbUsers(idMission);
    }


    @Override
    public Set<Competence> getCompetencesForMission(Long missionId) {
        Mymission mission = MymissionRepository.findById(missionId).orElse(null);
        return mission.getCompetences();
    }
}