package com.example.genericdetector.scheduling;

import com.example.genericdetector.entities.DetectorTaskConfig;
import com.example.genericdetector.producers.DetectorProducer;
import com.example.genericdetector.services.TaskTimeDetectorService;
import com.example.genericdetector.tasks.QueryRestEndpoint;

import java.util.*;

/**
 * Created by ANTONIO on 02/11/2017.
 */
public class DetectorTimerActionCollection {

    private Set<DetectorTimerAction> collection;
    private TaskTimeDetectorService taskTimeDetectorService;


    public DetectorTimerActionCollection(DetectorProducer detectorSource, TaskTimeDetectorService taskTimeDetectorService) {
        this.collection = new HashSet<>();

        List<DetectorTaskConfig> list = taskTimeDetectorService.findAll();
        for (DetectorTaskConfig t:list){
            System.out.println("TaskTimeDetector: " + t.toString());
            this.collection.add( new DetectorTimerAction( t, taskTimeDetectorService, new QueryRestEndpoint(t.getDetectorName(), detectorSource)) );
        }

    }

    public Collection<DetectorTimerAction> getCollection() {
        return new ArrayList<>(collection);
    }

    public void addTimerTask(DetectorTimerAction detectorTimerAction){
        collection.add(detectorTimerAction);


    }

    public boolean update(DetectorTimerAction detectorTimerAction){
        boolean resp = false;
        DetectorTimerAction tOld = collection.stream().findFirst().filter(t -> t.equals(detectorTimerAction)).get();

        if (tOld!=null){
            updateTimerTaskValues(tOld, detectorTimerAction);
            resp = true;
        }

        return resp;
    }


    private void updateTimerTaskValues(DetectorTimerAction old, DetectorTimerAction newT){
//        if( old!=null && newT!=null ){
//            old.setFixedRate(newT.getFixedRate());
//            old.setName(newT.getName());
//        }
    }

    public void removeTimerTask(DetectorTimerAction detectorTimerAction){
        collection.remove(detectorTimerAction);
    }


}
