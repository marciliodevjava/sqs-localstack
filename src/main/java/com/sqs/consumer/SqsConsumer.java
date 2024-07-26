package com.sqs.consumer;

import com.sqs.record.MyMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:message/sqs.properties")
public class SqsConsumer {

    @SqsListener("${SQS.S.0001}")
    public void listen(MyMessage message) {
        System.out.println("Message received: " + message.content());
    }
}
