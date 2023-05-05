package com.groupe4.pidev.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_paiment ;
    //zid les attribus mteik
}
