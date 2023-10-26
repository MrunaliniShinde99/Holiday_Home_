package com.holiday.home.dao;

import com.holiday.home.models.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlaceDAO extends JpaRepository<Places, Long> {
   }
