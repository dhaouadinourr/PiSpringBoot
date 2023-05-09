package com.groupe4.pidev.services.Impl;

import com.groupe4.pidev.entities.Comment;
import com.groupe4.pidev.entities.CommentResponseBody;
import com.groupe4.pidev.repositories.ArticleRepo;
import com.groupe4.pidev.repositories.CommentRepo;
import com.groupe4.pidev.services.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements ICommentService {
    private CommentRepo commentRepo;
    private ArticleRepo articleRepo;

    @Override
    public Comment editComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public void deleteComment(Long id, Long idA) {
        commentRepo.deleteById(id);
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentRepo.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findallComment() {
        return null;
    }

    @Override
    public void createComment(Long articleId, Comment comment) {
        comment.setArticle(articleRepo.findArticleById(articleId));
        commentRepo.save(comment);
    }

}
