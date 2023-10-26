package com.holiday.home.controllers;

import com.holiday.home.dto.AddPlaceDTO;
import com.holiday.home.exceptions.FileUploadException;
import com.holiday.home.models.Feedback;
import com.holiday.home.models.Places;
import com.holiday.home.models.Customer;
import com.holiday.home.services.AuthService;
import com.holiday.home.services.FeedbackService;
import com.holiday.home.services.FilesStorageService;
import com.holiday.home.services.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

    @Value("${files.upload.url}")
    private String BASE_PATH;
    @Autowired
    PlaceService placeService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    FilesStorageService storageService;

    @Autowired
    AuthService authService;

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(value = "/place/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Places> addPlace(@ModelAttribute AddPlaceDTO addPlaceDTO,HttpServletRequest request){
      // if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("admin")){ 
          	
    	log.info("UPLOADING IMAGE");
        String fileUrl = storageService.save(addPlaceDTO.getImage()).orElseThrow(FileUploadException::new);
        var place = new Places();
        place.setDescription(addPlaceDTO.getDescription());
        place.setName(addPlaceDTO.getName());
        place.setTouristAttraction(addPlaceDTO.getTouristAttraction());
        place.setAmenities(addPlaceDTO.getAmenities());
        place.setAvailableFrom(LocalDate.parse(addPlaceDTO.getAvailableFrom()));
        place.setAvailableTill(LocalDate.parse(addPlaceDTO.getAvailableTill()));
        place.setAddress(addPlaceDTO.getAddress());
        place.setImageUrl(BASE_PATH + fileUrl);
        log.info("ADDING PLACE");
        var result = placeService.createPlace(place);
        log.info("PLACE ADDED");
        return ResponseEntity.ok(result);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/place/all")
    public ResponseEntity<List<Places>> getAllPlaces(){
        log.info("FETCHING ALL PLACES");
        return ResponseEntity.ok(placeService.getAllPlaces());
    }
    @Operation(security = @SecurityRequirement(name = "bearerAuth")) // This is only for swagger, to bind token
    @GetMapping("/user/all")
    public ResponseEntity<List<Customer>> getAllUser(){
        log.info("FETCHING ALL USERS");
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/feedback/all")
    public ResponseEntity<List<Feedback>> getAllFeedbacks(){
        log.info("FETCHING ALL FEEDBACKS");
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }


}
