package com.example.genericdetector.services;

import com.example.genericdetector.entities.DetectorTaskConfig;
import com.example.genericdetector.repositories.TaskTimeDetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTimeDetectorService {

    private TaskTimeDetectorRepository taskTimeDetectorRepository;

    @Autowired
    public TaskTimeDetectorService(TaskTimeDetectorRepository taskTimeDetectorRepository) {
        this.taskTimeDetectorRepository = taskTimeDetectorRepository;
    }

    public DetectorTaskConfig findOne(String s) {
        return taskTimeDetectorRepository.findOne(s);
    }

    public List<DetectorTaskConfig> findAll() {
        return taskTimeDetectorRepository.findAll();
    }
}
