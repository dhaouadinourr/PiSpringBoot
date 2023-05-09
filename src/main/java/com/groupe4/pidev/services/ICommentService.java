package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Comment;
import com.groupe4.pidev.entities.CommentResponseBody;

import java.util.List;

public interface ICommentService {
    Comment editComment(Comment comment);
    void deleteComment(Long id, Long idA);
    Comment findCommentById(Long id);
    List<Comment> findallComment();
    void createComment(Long articleId, Comment comment);


}
