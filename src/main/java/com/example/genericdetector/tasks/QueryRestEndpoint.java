package com.example.genericdetector.tasks;

import com.example.genericdetector.producers.DetectorProducer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by ANTONIO on 05/11/2017.
 */
public class QueryRestEndpoint implements Runnable{

    private DetectorProducer detectorSource;
    private String channelName;


    @Autowired
    public QueryRestEndpoint(String channelName, DetectorProducer detectorSource) {
        this.detectorSource = detectorSource;
        this.channelName = channelName;
    }

    @Override
    public void run() {
        String message = new Date().toString();
        detectorSource.timerMessageSource(channelName, message);

    }
}
