package com.sqs.resource;

import com.sqs.record.MyMessage;
import com.sqs.service.impl.MessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageResource {
    @Autowired
    private MessageInterface messageInterface;

    @PostMapping("/sqs")
    public ResponseEntity sendSqs(@RequestBody MyMessage message) {
        Boolean messageSend = messageInterface.sendSqs(message);
        Map<String, String> response = new HashMap<>();
        if (messageSend) {
            response.put("message", "Mensagem SQS enviada com sucesso.");
            return ResponseEntity.status(201).body(response);
        }
        response.put("message", "Mensagem SQS não enviada.");
        return ResponseEntity.status(503).body(response);
    }

    @PostMapping("/sns")
    public ResponseEntity sendSns(@RequestBody MyMessage message) {
        Boolean messageSend = messageInterface.sendSns(message);
        Map<String, String> response = new HashMap<>();
        if (messageSend) {
            response.put("message", "Mensagem SNS enviada com sucesso.");
            return ResponseEntity.status(201).body(response);
        }
        response.put("message", "Mensagem SNS não enviada.");
        return ResponseEntity.status(503).body(response);
    }
}
