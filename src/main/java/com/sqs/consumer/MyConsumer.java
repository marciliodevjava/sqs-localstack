package com.sqs.consumer;

import com.sqs.record.MyMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sqs/sqs.properties")
public class MyConsumer {

    @SqsListener("${SQS.0001}")
    public void listen(MyMessage message) {
        System.out.println("Message received: " + message.content());
    }
}
