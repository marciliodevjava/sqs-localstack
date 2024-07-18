package com.sqs.service.impl;

import com.sqs.record.MyMessage;

public interface MessageInterface {
    Boolean send(MyMessage messageSendDto);
}
