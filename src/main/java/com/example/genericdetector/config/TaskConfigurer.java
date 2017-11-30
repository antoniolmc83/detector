package com.example.genericdetector.config;


import com.example.genericdetector.scheduling.DetectorTimerAction;
import com.example.genericdetector.scheduling.DetectorTimerActionCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by ANTONIO on 01/11/2017.
 */
@Configuration
@EnableScheduling
public class TaskConfigurer implements SchedulingConfigurer{

    private Environment env;
    private DetectorTimerActionCollection timerTaskCollection;

    @Autowired
    public TaskConfigurer(DetectorTimerActionCollection timerTaskCollection) {
        this.timerTaskCollection = timerTaskCollection;

        System.out.println("Creating TaskConfigurer " + timerTaskCollection.getCollection().size());
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
        System.out.println("Register 0");
        for(DetectorTimerAction task : timerTaskCollection.getCollection()){

            System.out.println("Register 1");

            scheduledTaskRegistrar.addTriggerTask(new Runnable() {
                @Override
                public void run() {
                    task.performAction();
                }
            }, new Trigger() {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext) {
                    Calendar nextExecutionTime =  new GregorianCalendar();
                    Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                    nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//                nextExecutionTime.add(Calendar.MILLISECOND, env.getProperty("myRate", Integer.class)); //you can get the value from wherever you want
                    nextExecutionTime.add(Calendar.MILLISECOND, task.getDetectorTaskConfig().getFixedDelay()); //you can get the value from wherever you want

                    return nextExecutionTime.getTime();
                }
            });
        }



    }


    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }




}
