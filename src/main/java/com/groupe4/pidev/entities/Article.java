package com.groupe4.pidev.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name= "content")
    private String content;

    @Column(name= "image")
    private byte[] image;
    private Date createdAt;
  //  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
  //  private List<Vote> votes = new ArrayList<>();
    private Double etoile;

    private Date updatedAt;
    @ElementCollection
    private Map<Long,Double> ClientEtoile;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
   // public int getScore(double averageScore) {
      //  return this.votes.stream().mapToInt(Vote::getNote).sum();
   // }


}
