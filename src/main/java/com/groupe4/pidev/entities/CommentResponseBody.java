package com.groupe4.pidev.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseBody {
    Long id;
    private String name;
    private String body;
    private Long article_id;
    private Article article;
}
