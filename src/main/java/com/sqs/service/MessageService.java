package com.sqs.service;

import com.sqs.record.MyMessage;
import com.sqs.service.impl.MessageInterface;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageService implements MessageInterface {
    @Autowired
    private SqsTemplate sqsTemplate;
    @Value("${SQS.0001}")
    private String SQS_1;

    @Override
    public Boolean send(MyMessage messageSendDto) {
        SendResult messageSend = sqsTemplate.send(SQS_1, messageSendDto);
        if(Objects.nonNull(messageSend.messageId())) return true;
        return false;
    }
}
