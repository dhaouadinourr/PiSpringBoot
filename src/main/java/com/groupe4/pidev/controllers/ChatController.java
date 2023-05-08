package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.ChatMessage;
import com.groupe4.pidev.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("chat/")
@AllArgsConstructor
public class ChatController {

    @Autowired
    private ChatService chatService;
    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public ChatMessage chat(@Payload ChatMessage chat) {
        chatService.add(chat);
        System.out.println(chat.getContent());
        return chat;
    }
    @PutMapping("/updatechat/{ids}/{id}")
    public ResponseEntity<?> getchat(@RequestBody ChatMessage chat, @PathParam("ids") Long ids, @PathParam("id") Long id){
        chatService.update(chat,ids,id);
        return new ResponseEntity<>(chatService.update(chat,ids,id), HttpStatus.OK);
    }
    @GetMapping("/getchatbetwinsandr/{ids}/{idr}")
    public ResponseEntity<?> getchat(@PathVariable("ids") Long ids,@PathVariable("idr") Long idr){
        chatService.getbetwinsenderandreciver(ids,idr);
        return new ResponseEntity<>(chatService.getbetwinsenderandreciver(ids,idr), HttpStatus.OK);
    }
}
