package com.matera.eod.activity_analyst.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matera.eod.activity_analyst.record.ActivityProcessResult;
import com.matera.eod.activity_analyst.service.ActivityProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ActivityProcessResultConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ActivityProcessService service;

    @KafkaListener(topics = "${topic.activity.result}", groupId = "${topic.activity.result.group.id.config}",
            containerFactory = "kafkaListenerContainerFactory")
    public void activityResultListener(String  activityProcessResultContent) throws Exception {
        ActivityProcessResult result = objectMapper.readValue(activityProcessResultContent, ActivityProcessResult.class);
        String msg = MessageFormat.format("Received Process Activity Result from Activity {0}, processing id: {1},  " +
                "process name: {2}", result.activityName(), String.valueOf(result.processingId()),
                result.processName());
        System.out.println(msg);
        service.insertActivityProcessResultAndSendInformationIfAllActivitiesIsFinihed(result);
    }
}
