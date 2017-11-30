package com.example.genericdetector.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by ANTONIO on 05/11/2017.
 */

@EnableBinding//(Source.class)
@Component
public class DetectorProducer {
    private static final Logger log = LoggerFactory.getLogger(DetectorProducer.class);
//    private Source source;
    private BinderAwareChannelResolver resolver;

//    @Autowired
//    public DetectorProducer(Source source){
//        this.source = source;
//    }
    @Autowired
    public DetectorProducer(BinderAwareChannelResolver resolver){
        this.resolver = resolver;
    }

    public void timerMessageSource(String channelName, String message) {
        log.info("Channel: " + channelName + "Sending message  " + message);
//        source.output().send(MessageBuilder.withPayload(message).build());
        Message<String> msg = new GenericMessage<>(message, Collections.singletonMap("type", "order"));
        resolver.resolveDestination(channelName).send(msg);;
    }
}
