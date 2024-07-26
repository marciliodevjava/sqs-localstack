package com.sqs.service.impl;

import com.sqs.record.MyMessage;
import software.amazon.awssdk.services.sns.SnsClient;

public interface MessageInterface {
    Boolean sendSqs(MyMessage messageSendDto);

    Boolean sendSns(MyMessage message);
}
