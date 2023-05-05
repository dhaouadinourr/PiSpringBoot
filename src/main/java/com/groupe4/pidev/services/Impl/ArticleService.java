package com.groupe4.pidev.services.Impl;

import com.groupe4.pidev.entities.*;
import com.groupe4.pidev.repositories.ArticleRepo;
import com.groupe4.pidev.repositories.UserRepo;
import com.groupe4.pidev.services.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService implements IArticleService {
    private ArticleRepo articleRepo;
    private UserRepo userRepo;

    @Override
    public void addArticle(ArticleRequestBody articleRequestBody, MultipartFile image) throws IOException {
        //Article article = ArticleMapper.INSTANCE.articleRequestBodyToArticle(articleRequestBody);
        Article article = new Article();
        article.setTitle(articleRequestBody.getTitle());
        article.setDescription(articleRequestBody.getDescription());
        article.setContent(articleRequestBody.getContent());
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        article.setImage(image.getBytes());
        articleRepo.save(article);
    }

    @Override
    public void editArticle(Long id, ArticleRequestBody articleRequestBody, MultipartFile image) throws IOException {
        Article article = articleRepo.findArticleById(id);
        if(!articleRequestBody.getTitle().isEmpty()) article.setTitle(articleRequestBody.getTitle());
        if(!articleRequestBody.getDescription().isEmpty())article.setDescription(articleRequestBody.getDescription());
        if(!articleRequestBody.getContent().isEmpty())article.setContent(articleRequestBody.getContent());
        if(!image.isEmpty())article.setImage(image.getBytes());
        article.setUpdatedAt(new Date());
        articleRepo.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepo.deleteById(id);

    }

    @Override
    public List<ArticleResponseBody> findAllArticle() {
        List<Article> articleList = articleRepo.findAll();
        List<Article> ordredList = articleList.stream()
                .sorted(Comparator.comparing(Article::getCreatedAt).reversed()).collect(Collectors.toList());
        List<ArticleResponseBody> articles = new ArrayList<>();
        for(var article : ordredList){
            articles.add(articleMapper(article));
        }
        return articles;
    }

    public ArticleResponseBody articleMapper(Article article){
        ArticleResponseBody articleResponseBody = new ArticleResponseBody();
        articleResponseBody.setId(article.getId());
        articleResponseBody.setTitle(article.getTitle());
        articleResponseBody.setCreationDate(article.getCreatedAt().toString());
        articleResponseBody.setImage(article.getImage());
        return articleResponseBody;
    }

    @Override
    public ArticleDetailsResponseBody findArticleById(Long id) {
        ArticleDetailsResponseBody articleDetailsResponseBody = new ArticleDetailsResponseBody();
        Article article = articleRepo.findById(id).orElse(null);
        articleDetailsResponseBody.setId(article.getId());
        articleDetailsResponseBody.setEtoile(article.getEtoile());
        articleDetailsResponseBody.setClientEtoile(article.getClientEtoile());
        articleDetailsResponseBody.setTitle(article.getTitle());
        articleDetailsResponseBody.setDescription(article.getDescription());
        articleDetailsResponseBody.setContent(article.getContent());
        articleDetailsResponseBody.setCreatedAt(article.getCreatedAt().toString());
        articleDetailsResponseBody.setUpdatedAt(article.getCreatedAt().toString());
        articleDetailsResponseBody.setImage(article.getImage());
        List<CommentResponseBody> commentList = new ArrayList<>();
        for(var commentVar : article.getComments()){
            commentList.add(commentMapper(commentVar));
        }
        articleDetailsResponseBody.setComments(commentList);
        return articleDetailsResponseBody;
    }

    @Override
    public void calculeEtoile(Double rev, Long idA, Long idC) {
        Article art = articleRepo.findArticleById(idA);
        Double rectif = null;
        User c = (User) userRepo.findById(idC).orElse(null);
        Map<Long, Double> temp = art.getClientEtoile();
        Double sum = 0.0;
        if (((art != null)) && (rev >= 0 && rev <= 5)) {
            temp.put(idC, rev);
            for (Double value : temp.values()) {
                sum += value;
            }
            rectif = sum / temp.size();
            if (rectif >= 0 && rectif < 0.5) {
                art.setEtoile(0.0);
                art.setClientEtoile(temp);
            } else if (rectif >= 0.5 && rectif < 1) {
                art.setEtoile(0.5);
                art.setClientEtoile(temp);
            } else if (rectif >= 1 && rectif < 1.5) {
                art.setEtoile(1.0);
                art.setClientEtoile(temp);
            } else if (rectif >= 1.5 && rectif < 2) {
                art.setEtoile(1.5);
                art.setClientEtoile(temp);
            } else if (rectif >= 2 && rectif < 2.5) {
                art.setEtoile(2.0);
                art.setClientEtoile(temp);
            } else if (rectif >= 2.5 && rectif < 3) {
                art.setEtoile(2.5);
                art.setClientEtoile(temp);
            } else if (rectif >= 3 && rectif < 3.5) {
                art.setEtoile(3.0);
                art.setClientEtoile(temp);
            } else if (rectif >= 3.5 && rectif < 4) {
                art.setEtoile(3.5);
                art.setClientEtoile(temp);
            } else if (rectif >= 4 && rectif < 4.5) {
                art.setEtoile(4.0);
                art.setClientEtoile(temp);
            } else if (rectif >= 4.5 && rectif < 4.75) {
                art.setEtoile(4.5);
                art.setClientEtoile(temp);
            } else if (rectif >= 4.75 && rectif <= 5) {
                art.setEtoile(5.0);
                art.setClientEtoile(temp);
            }
            updateArticle(art);
        }
    }

    private Article updateArticle(Article art) {
        return articleRepo.save(art);
    }


    public CommentResponseBody commentMapper(Comment comment){
        CommentResponseBody commentResponseBody = new CommentResponseBody();
        commentResponseBody.setId(comment.getId());
        commentResponseBody.setName(comment.getName());
        commentResponseBody.setBody(comment.getBody());
        return commentResponseBody;
    }
}
