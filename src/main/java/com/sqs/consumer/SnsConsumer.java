package com.sqs.consumer;

import com.sqs.record.MyMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:message/sns.properties")
public class SnsConsumer {


    public void listSubscriptions(MyMessage message) {
        System.out.println("Message recived: " + message.content());
    }
}