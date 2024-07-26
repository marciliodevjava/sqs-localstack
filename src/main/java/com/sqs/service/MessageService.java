package com.sqs.service;

import com.sqs.record.MyMessage;
import com.sqs.service.impl.MessageInterface;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
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
    @Autowired
    private SnsTemplate snsTemplate;

    @Value("${sqs.name.sqs-0001}")
    private String SQS_1;
    @Value("${sns.name.sns-0001}")
    private String SNS_1;

    @Override
    public Boolean sendSqs(MyMessage messageSendDto) {
        SendResult messageSend = sqsTemplate.send(to -> to.queue(SQS_1).payload(messageSendDto));
        if (Objects.nonNull(messageSend.messageId())) return true;
        return false;
    }

    @Override
    public Boolean sendSns(MyMessage message) {
        snsTemplate.sendNotification(SNS_1, SnsNotification.of(message));
        return true;
    }

}
