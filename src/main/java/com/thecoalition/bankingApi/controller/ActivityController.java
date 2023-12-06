package com.thecoalition.bankingApi.controller;

import com.thecoalition.bankingApi.response.ActivityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityResponse activityResponse;


    @GetMapping("/all")
    public ResponseEntity<?> getAllActivities() {
        return activityResponse.getAllActivities();
    }




}
