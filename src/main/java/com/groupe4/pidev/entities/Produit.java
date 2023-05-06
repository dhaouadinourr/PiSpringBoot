package com.groupe4.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

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
    String nom;
    String description;
    String image;
    @CreationTimestamp
    LocalDate datedecreation;
    Double prix;
    int quantity;

    @ManyToOne
            @JsonIgnore
    ProduitCategory produitCategory;

}

