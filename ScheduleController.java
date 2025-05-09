/*
 * Smart Railway Scheduler - Java (Spring Boot + REST API)
 * Description:
 * This is a high-end Java-based backend service for a Smart Railway Schedule Optimizer.
 * It uses Spring Boot to expose REST APIs and simulate dynamic train schedule adjustments
 * based on passenger demand data. This project can be connected to a frontend dashboard or integrated
 * into a real-time booking system.
 */

// File: Application.java
package com.smart.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// File: model/TrainSchedule.java
package com.smart.scheduler.model;

public class TrainSchedule {
    private String trainId;
    private String source;
    private String destination;
    private int capacity;
    private int bookedSeats;

    public TrainSchedule(String trainId, String source, String destination, int capacity, int bookedSeats) {
        this.trainId = trainId;
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        this.bookedSeats = bookedSeats;
    }

    public boolean needsExpansion() {
        return bookedSeats >= capacity * 0.9;
    }

    // Getters and setters...

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}

// File: controller/ScheduleController.java
package com.smart.scheduler.controller;

import com.smart.scheduler.model.TrainSchedule;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final List<TrainSchedule> schedules = new ArrayList<>();

    @PostMapping("/add")
    public String addSchedule(@RequestBody TrainSchedule schedule) {
        schedules.add(schedule);
        return "Train schedule added.";
    }

    @GetMapping("/all")
    public List<TrainSchedule> getAllSchedules() {
        return schedules;
    }

    @GetMapping("/overloaded")
    public List<TrainSchedule> getOverloadedTrains() {
        List<TrainSchedule> overloaded = new ArrayList<>();
        for (TrainSchedule ts : schedules) {
            if (ts.needsExpansion()) {
                overloaded.add(ts);
            }
        }
        return overloaded;
    }
}

/*
 * How to Run:
 * 1. Set up a Maven or Gradle project and include Spring Boot dependencies.
 * 2. Build and run the application.
 * 3. Use Postman or a frontend to interact with API endpoints:
 *    - POST /api/schedule/add -> Add train schedule
 *    - GET /api/schedule/all -> Get all schedules
 *    - GET /api/schedule/overloaded -> Get trains needing expansion
 *
 * Technologies: Java, Spring Boot, REST API, JSON, Maven/Gradle
 */
