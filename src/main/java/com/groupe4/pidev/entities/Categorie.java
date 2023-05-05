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
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_categ;
    String nomCateg;
    //zid les attribus mteik
    @OneToMany(mappedBy ="Categ")
    Set<Evenement> evenements;

}
