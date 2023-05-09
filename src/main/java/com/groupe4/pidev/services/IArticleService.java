package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IArticleService {
    Long addArticle(ArticleRequestBody articleRequestBody);
    Long editArticle(Long id, Article article) throws IOException;;
    void deleteArticle(Long id);
    List<ArticleResponseBody> findAllArticle();
    ArticleDetailsResponseBody findArticleById(Long id);
    Article findArtiById(Long id);
    void calculeEtoile(Double rev, Long idA, Long idC);
}

