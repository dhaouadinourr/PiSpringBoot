package com.groupe4.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date dateDebut;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date dateFin;

    String picture;


 @ManyToMany
    Set<User> userevent;
    @ManyToOne
    Categorie categ;
    @OneToMany(mappedBy ="events")
    Set<EventComment> eventComments;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "image", fetch = FetchType.EAGER)
    private Set<MultiPicture> products;
}
