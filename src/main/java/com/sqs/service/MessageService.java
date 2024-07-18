package com.sqs.service;

import com.sqs.record.MyMessage;
import com.sqs.service.impl.MessageInterface;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.Objects;

@Service
public class MessageService implements MessageInterface {
    @Autowired
    private SqsTemplate sqsTemplate;
    @Autowired
    private SnsClient snsClient;

    @Value("${sqs.name.sqs-0001}")
    private String SQS_1;
    @Value("${sns.name.sns-0001}")
    private String SNS_1;

    @Override
    public Boolean sendSqs(MyMessage messageSendDto) {
        SendResult messageSend = sqsTemplate.send(SQS_1, messageSendDto);
        if (Objects.nonNull(messageSend.messageId())) return true;
        return false;
    }

    @Override
    public Boolean sendSns(MyMessage message) {
        try {
            PublishRequest publishRequest = PublishRequest.builder()
                    .topicArn(SNS_1)
                    .message(String.valueOf(message)) // Certifique-se de que MyMessage tenha um método getMessage()
                    .build();

            PublishResponse publishResponse = snsClient.publish(publishRequest);
            return Objects.nonNull(publishResponse.messageId());
        } catch (Exception e) {
            return false;
        }
    }
}
