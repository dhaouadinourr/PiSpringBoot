package com.groupe4.pidev.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleResponseBody {
    Long id;
    private String title;
    private String creationDate;
    private byte[] image;
}
