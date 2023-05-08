package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.ChatMessage;
import com.groupe4.pidev.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllBySender(User e);

    List<ChatMessage> findBySenderIdAndReciverIdOrderBySenddateDesc(Long t, Long y);

}