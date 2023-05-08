package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.Comment;
import com.groupe4.pidev.services.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("comment")
public class CommentController {
    private ICommentService commentService;

    @PostMapping("/add/{articleId}")
    public void addComment(@PathVariable Long articleId, @RequestBody Comment comment){
        commentService.createComment(articleId, comment);
    }
    @DeleteMapping("/delete/{id}/{idA}")
    public void deleteComment(@PathVariable Long id, @PathVariable("idA") Long idA){
        commentService.deleteComment(id,idA);
    }
}
