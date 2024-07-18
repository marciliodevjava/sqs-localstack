package com.sqs.service.impl;

import com.sqs.record.MyMessage;

public interface MessageInterface {
    Boolean sendSqs(MyMessage messageSendDto);

    Boolean sendSns(MyMessage message);
}
