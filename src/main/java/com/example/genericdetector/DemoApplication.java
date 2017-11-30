package com.example.genericdetector;

import com.example.genericdetector.scheduling.DetectorTimerActionCollection;
import com.example.genericdetector.producers.DetectorProducer;
import com.example.genericdetector.services.TaskTimeDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class DemoApplication {

	private static ConfigurableApplicationContext configurableApplicationContext = null;

	public static void main(String[] args) {
		configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);
	}


	@Autowired
	@Bean
	public DetectorTimerActionCollection createTimerTaskCollection(DetectorProducer detectorSource, TaskTimeDetectorService taskTimeDetectorService){
		return new DetectorTimerActionCollection(detectorSource, taskTimeDetectorService);
	}


}
