package com.holiday.home.services;

import com.holiday.home.dao.FeedbackDAO;
import com.holiday.home.dao.PlaceDAO;
import com.holiday.home.dao.UserDAO;
import com.holiday.home.dto.FeedbackDTO;
import com.holiday.home.exceptions.ResourceNotFoundException;
import com.holiday.home.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    FeedbackDAO feedbackDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    PlaceDAO placeDAO;

    public List<Feedback> getAllFeedback(){
        return feedbackDAO.findAll();
    }

    public Feedback createFeedback(FeedbackDTO feedbackDTO){
        Feedback feedback = new Feedback();
        feedback.setId(null);
        feedback.setUser(userDAO.findById(feedbackDTO.getUserId()).orElseThrow(ResourceNotFoundException::new));
        feedback.setPlace(placeDAO.findById(feedbackDTO.getPlaceId()).orElseThrow(ResourceNotFoundException::new));
        feedback.setCreatedOn(LocalDateTime.now());
        feedback.setFeedback(feedbackDTO.getFeedback());
        feedbackDAO.save(feedback);
        return feedback;
    }
}
