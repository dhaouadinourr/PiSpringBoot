package com.groupe4.pidev.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
@Slf4j
@Setter
@Getter
public class ArticleRequestBody {
    private String title;
    private String description;
    private String content;
}
