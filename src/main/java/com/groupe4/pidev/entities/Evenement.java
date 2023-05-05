package com.groupe4.pidev.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evenement {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_event;
    String nomEvent;
    String details;
    LocalDate dateDebut;
    LocalDate dateFin;


@ManyToMany
    Set<User> userevent;
    @ManyToOne
    Categorie Categ;
    @OneToMany(mappedBy ="events")
    Set<EventComment> eventComments;

}
