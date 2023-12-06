package com.thecoalition.bankingApi.response;

import com.thecoalition.bankingApi.dto.Body;
import com.thecoalition.bankingApi.model.Activity;
import com.thecoalition.bankingApi.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivityResponse {

    @Autowired
    private final ActivityService activityService;


    public ActivityResponse(ActivityService activityService) {
        this.activityService = activityService;
    }

    public ResponseEntity<?> getAllActivities() {
        Body body = new Body();
        try {

            Iterable<Activity> activities = activityService.getAllActivities();


            body.setData(activities);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Success");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {

            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error fetching account activities");
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
