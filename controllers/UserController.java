package com.holiday.home.controllers;

import com.holiday.home.dto.FeedbackDTO;
import com.holiday.home.dto.SearchPlaceDTO;
import com.holiday.home.models.Feedback;
import com.holiday.home.models.Places;
import com.holiday.home.services.FeedbackService;
import com.holiday.home.services.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    FeedbackService feedbackService;
    @Autowired
    PlaceService placeService;
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/feedback/create")
    public ResponseEntity<Feedback> createFeedbacks(@RequestBody FeedbackDTO feedbackDTO){
        log.info("SAVING NEW FEEDBACK");
        return ResponseEntity.ok(feedbackService.createFeedback(feedbackDTO));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/place/search")
    public ResponseEntity<List<Places>> searchPlace(@RequestBody SearchPlaceDTO searchPlaceDTO){
        log.info("SEARCHING PLACES");
        return ResponseEntity.ok(placeService.searchPlaces(searchPlaceDTO));
    }
}
