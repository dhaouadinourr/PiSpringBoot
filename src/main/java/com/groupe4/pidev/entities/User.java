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
 @Enumerated(EnumType.STRING)
 Role role;
@ManyToMany
(mappedBy="userevent")
    Set<Evenement> events;

}
