package com.example.genericdetector.repositories;

import com.example.genericdetector.entities.DetectorTaskConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTimeDetectorRepository extends JpaRepository<DetectorTaskConfig, String>{
}
