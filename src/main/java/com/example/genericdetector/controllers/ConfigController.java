package com.example.genericdetector.controllers;

import com.example.genericdetector.scheduling.DetectorTimerAction;
import com.example.genericdetector.scheduling.DetectorTimerActionCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by ANTONIO on 01/11/2017.
 */
@RestController
@RequestMapping
public class ConfigController {

    private DetectorTimerActionCollection timerTaskCollection;

    @Autowired
    public ConfigController(DetectorTimerActionCollection timerTaskCollection) {
        this.timerTaskCollection = timerTaskCollection;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inittimertasks")
    public Collection<DetectorTimerAction> getInitTimerTasks(){
        return timerTaskCollection.getCollection();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/inittimertasks", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Collection<DetectorTimerAction> updateTimerTask(@RequestBody DetectorTimerAction detectorTimerAction){
        timerTaskCollection.update(detectorTimerAction);
        return timerTaskCollection.getCollection();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/inittimertasks", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Collection<DetectorTimerAction> addTimerTask(@RequestBody DetectorTimerAction detectorTimerAction){
        timerTaskCollection.addTimerTask(detectorTimerAction);
        return timerTaskCollection.getCollection();
    }





}
