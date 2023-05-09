package com.groupe4.pidev.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import java.util.Map;

@Slf4j
@Setter
@Getter
public class ArticleRequestBody {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String picture;
    private Double etoile;
    private Map<Long,Double> ClientEtoile;
}
