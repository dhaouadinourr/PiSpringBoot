package com.groupe4.pidev.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_produit;
    //zid les attribus mteik

}

