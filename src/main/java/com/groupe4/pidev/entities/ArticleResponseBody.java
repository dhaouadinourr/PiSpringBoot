package com.groupe4.pidev.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Setter
@Getter
public class ArticleResponseBody {
    Long id;
    private String title;
    private Double etoile;
    private Map<Long,Double> ClientEtoile;

    private String creationDate;
    private String picture;
}
