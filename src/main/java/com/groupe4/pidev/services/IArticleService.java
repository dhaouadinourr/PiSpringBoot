package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Article;
import com.groupe4.pidev.entities.ArticleDetailsResponseBody;
import com.groupe4.pidev.entities.ArticleRequestBody;
import com.groupe4.pidev.entities.ArticleResponseBody;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IArticleService {
    void addArticle(ArticleRequestBody article, MultipartFile image) throws IOException;
    void editArticle(Long id, ArticleRequestBody article, MultipartFile image) throws IOException;;
    void deleteArticle(Long id);
    List<ArticleResponseBody> findAllArticle();
    ArticleDetailsResponseBody findArticleById(Long id);
    void calculeEtoile(Double rev, Long idA, Long idC);
}

