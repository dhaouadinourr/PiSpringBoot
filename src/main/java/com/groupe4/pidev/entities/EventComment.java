package com.groupe4.pidev.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    Long idCom;
    String nomContenu;
    String pic;
    @ManyToOne
    Evenement events;
}
