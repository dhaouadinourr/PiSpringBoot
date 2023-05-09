package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.*;
import com.groupe4.pidev.services.IArticleService;
import com.groupe4.pidev.services.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("comment")
public class CommentController {
    private ICommentService commentService;
    IArticleService articleService;

    @PostMapping("/add/{articleId}")
    public void addComment(@PathVariable Long articleId, @RequestBody Comment comment){
        commentService.createComment(articleId, comment);
    }
    @DeleteMapping("/delete/{id}/{idA}")
    public void deleteComment(@PathVariable Long id, @PathVariable("idA") Long idA){
        commentService.deleteComment(id,idA);
    }
    @PutMapping("/update")
    public Comment update(@RequestBody Comment com
         ){
        List<String> badWords= Collections.unmodifiableList(Arrays.asList("bob","fuck","shit","sh*t","bitch","bastard","cunt","trash","wanker","nigga","slut","damn","sucker","cracker","poop","boob","buub","f*ck","b*tch"));;
        Article p=articleService.findArtiById(com.getArticle().getId());

        if(p==null){throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "NULL");}
        if(com.getBody().replaceAll("\\s+","").equals("")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Empty");
        }
        for(String bW : badWords){
            if(com.getBody().toLowerCase().replaceAll("\\s+","").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g").contains(bW)){
                throw new ResponseStatusException( HttpStatus.NOT_ACCEPTABLE, "Bad words");}
        }
        for(Comment co :p.getComments()){
            if(com.getArticle().equals(co.getArticle())){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duplicated");
            }
        }
        return commentService.editComment(com);
    }
}
