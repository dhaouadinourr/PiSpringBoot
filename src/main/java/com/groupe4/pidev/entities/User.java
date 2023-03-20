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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long ID_user;
 String nom_user;
 String prenom_user;
 String mail_user;
    //zid les attribus mteik ken fama
 @Enumerated(EnumType.STRING)
 Role role;
    //amatlek type enumeree
@ManyToMany
(mappedBy="userevent")
    Set<Evenement> events;
//hedhi teb3etni association maa l event

}
