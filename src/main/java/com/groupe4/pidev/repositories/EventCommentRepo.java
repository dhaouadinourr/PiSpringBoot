package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentRepo extends JpaRepository<EventComment,Long> {
}
