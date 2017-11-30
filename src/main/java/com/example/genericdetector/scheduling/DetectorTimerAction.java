package com.example.genericdetector.scheduling;

import com.example.genericdetector.entities.DetectorTaskConfig;
import com.example.genericdetector.services.TaskTimeDetectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Created by ANTONIO on 01/11/2017.
 */

public class DetectorTimerAction  {
    private static final Logger log = LoggerFactory.getLogger(DetectorTimerAction.class);

    private DetectorTaskConfig detectorTaskConfig;
    private Runnable runnable;
    private TaskTimeDetectorService taskTimeDetectorService;



    public DetectorTimerAction(DetectorTaskConfig detectorTaskConfig, TaskTimeDetectorService taskTimeDetectorService, Runnable runnable) {
        this.runnable = runnable;
        this.detectorTaskConfig = detectorTaskConfig;
        this.taskTimeDetectorService = taskTimeDetectorService;
    }


    public void performAction() {

        DetectorTaskConfig fromDB = taskTimeDetectorService.findOne(detectorTaskConfig.getDetectorName());
        log.debug("Task from DB" + fromDB.toString());
        if( fromDB != null ){

            if ( !detectorTaskConfig.equals(fromDB) ){
                //Merge detectorTaskConfig in memory
                detectorTaskConfig.setFixedDelay(fromDB.getFixedDelay());
                detectorTaskConfig.setPollDate(fromDB.getPollDate());
                detectorTaskConfig.setTimeRange(fromDB.getTimeRange());
                detectorTaskConfig.setEnabled(fromDB.getEnabled());
            }

            if( "1".equals(detectorTaskConfig.getEnabled())  ){
                if( runnable!=null ){
                    runnable.run();
                }else{
                    log.info("Runnable of " + detectorTaskConfig.getDetectorName()  + " is null");
                }
            }else {
                log.debug(detectorTaskConfig.getDetectorName() + " is not enabled");
            }


        }else {
            log.info( detectorTaskConfig.getDetectorName() + " is not in DB any more ");
        }

    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public DetectorTaskConfig getDetectorTaskConfig() {
        return detectorTaskConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetectorTimerAction that = (DetectorTimerAction) o;
        if (detectorTaskConfig==null && that.getDetectorTaskConfig() == null) return true;
        if (detectorTaskConfig==null && that.getDetectorTaskConfig() != null) return false;
        if (detectorTaskConfig!=null && that.getDetectorTaskConfig() == null) return false;

        return detectorTaskConfig.getDetectorName()!=null ? detectorTaskConfig.getDetectorName().equals(that.getDetectorTaskConfig().getDetectorName()) : that.getDetectorTaskConfig().getDetectorName() == null;
    }

    @Override
    public String toString() {
        return "DetectorTimerAction{" +
                "detectorTaskConfig=" + detectorTaskConfig +
                '}';
    }
}
