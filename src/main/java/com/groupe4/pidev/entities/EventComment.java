package com.groupe4.pidev.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCommentaire;

    Long idClient;

    String comment;

    Long likes;
    @JsonBackReference(value="eventcom")
    @ManyToOne

    Evenement events;
    @CreationTimestamp
    Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    Date lastUpdated;
}
