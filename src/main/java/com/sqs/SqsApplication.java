package com.sqs;

import com.sqs.record.MyMessage;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@SpringBootApplication
@PropertySource("classpath:message/generic.properties")
public class SqsApplication implements CommandLineRunner {

    @Autowired
    private SqsTemplate sqsTemplate;
    @Value("${SQS.G.0001}")
    private String SQS_1;

    @Autowired
    private SnsClient snsClient;
    @Value("${SNS.G.0001}")
    private String SNS_1;

    private final String MENSAGEM_SQS = "Minha sensagem enviado para fila de SQS";
    private final String MENSAGEM_SNS = "Minha sensagem enviado para fila de SNS";

    public static void main(String[] args) {
        SpringApplication.run(SqsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sqsTemplate.send(SQS_1, new MyMessage(MENSAGEM_SQS));
        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(SNS_1)
                .message(String.valueOf(MENSAGEM_SNS)) // Certifique-se de que MyMessage tenha um método getMessage()
                .build();

        PublishResponse publishResponse = snsClient.publish(publishRequest);
    }
}
