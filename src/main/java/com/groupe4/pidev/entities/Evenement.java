package com.groupe4.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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



    @ManyToMany(cascade = CascadeType.ALL)

    Set<User> userevent;
    @ManyToOne
    Categorie categ;
    @OneToMany(cascade=CascadeType.PERSIST,mappedBy = "events",fetch=FetchType.LAZY)
    @JsonManagedReference(value="eventcom")
    List<EventComment> comments;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "image", fetch = FetchType.EAGER)
    private Set<MultiPicture> products;


    public void addParticipant(User user) {
        this.userevent.add(user);
        user.getEvents().add(this);
    }

    public void removeParticipant(User user) {
        this.userevent.remove(user);
        user.getEvents().remove(this);
    }


}
