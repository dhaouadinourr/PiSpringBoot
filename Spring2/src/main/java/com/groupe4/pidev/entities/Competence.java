package com.groupe4.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.Set;




@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cname ;

    @JsonIgnore
    @ManyToMany(mappedBy = "competences")
    private Set<Mymission> missions;

}



