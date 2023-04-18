package com.groupe4.pidev.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mymission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title ;
    String description;
    String location;

    LocalDate dateDebut;

    LocalDate dateFin;
    Long nbPlaces;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Competence> competences;

    @ManyToMany(mappedBy = "missions",cascade = CascadeType.ALL)
    private Set<User> users;

}


