package com.sqs.resource;

import com.sqs.record.MyMessage;
import com.sqs.service.impl.MessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageResource {

    @Autowired
    private MessageInterface messageInterface;

    @PostMapping
    public ResponseEntity send(@RequestBody MyMessage message) {
        Boolean messageSend = messageInterface.send(message);
        if (messageSend) return ResponseEntity.status(201).body("Mensagem enviada com sucesso.");
        return ResponseEntity.status(503).body("Mensagem não enviada.");
    }
}
