package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Comment;

public interface ICommentService {
    Comment addComment(Comment comment);
    Comment editComment(Comment comment);
    void deleteComment(Long id);
    Comment findCommentById(Long id);

    void createComment(Long articleId, Comment comment);


}
