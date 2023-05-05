package com.groupe4.pidev.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ArticleDetailsResponseBody {
    private Long id;
    private String title;
    private String description;
    private String content;
    private byte[] image;
    private String createdAt;
    private String updatedAt;
    private List<CommentResponseBody> comments = new ArrayList<>();
    private Map<Long, Double> ClientEtoile;
    private Double etoile;
}
