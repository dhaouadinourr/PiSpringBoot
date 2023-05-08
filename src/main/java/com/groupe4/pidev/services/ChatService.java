package com.groupe4.pidev.services;


import com.groupe4.pidev.entities.ChatMessage;
import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.repositories.ChatRepository;
import com.groupe4.pidev.security.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {
    private ChatRepository chatRepository;
    private UserServiceImpl userService;
    public ChatMessage update(ChatMessage e, Long ids, Long id) {
        return chatRepository.findById(e.getId())
                .map(element -> {
                    element.setSender(userService.findById(ids));
                    element.setReciver(userService.findById(id));
                    return chatRepository.save(element);
                })
                .orElseGet(() -> {
                    e.setId(id);
                    return chatRepository.save(e);
                });
    }
    public ChatMessage add(ChatMessage chat){

        chat.setSenddate(LocalDateTime.now());
        return chatRepository.save(chat);
    }
    public List<ChatMessage> get(User e){
        return chatRepository.findAllBySender(e);
    }
    public List<ChatMessage>getbetwinsenderandreciver(Long ids,Long idr){
        return chatRepository.findBySenderIdAndReciverIdOrderBySenddateDesc(ids,idr);
    }

}