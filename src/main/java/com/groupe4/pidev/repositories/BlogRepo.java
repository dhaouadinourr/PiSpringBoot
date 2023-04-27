package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository <Blog,Long> {
}
