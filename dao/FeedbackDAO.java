package com.holiday.home.dao;

import com.holiday.home.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, Long> {
}
