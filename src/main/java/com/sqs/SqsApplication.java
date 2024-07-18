package com.sqs;

import com.sqs.record.MyMessage;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:message/sqs.properties")
public class SqsApplication implements CommandLineRunner {

    @Autowired
    private SqsTemplate sqsTemplate;

    @Value("${SQS.0001}")
    private String SQS_1;

    public static void main(String[] args) {
        SpringApplication.run(SqsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sqsTemplate.send(SQS_1, new MyMessage("Minha sensagem enviado para fila"));

    }
}
