package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository <Article,Long> {

    Article findArticleById(Long id);
    List<Article> findByTitleContaining(String searchTerm);
   // List<Article> findByTitleContainingIgnoreCase(String searchTerm, Pageable pageable);
 /*  @Modifying
   @Query("UPDATE Article a SET a.votes = a.votes + 1 WHERE a.id = ?1")
   int addVote(Long id);

    @Modifying
    @Query("UPDATE Article a SET a.votes = a.votes - 1 WHERE a.id = ?1")
    int removeVote(Long id);*/
}
