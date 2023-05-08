package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository <Article,Long> {
}
