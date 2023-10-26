package com.holiday.home.services;

import com.holiday.home.dao.PlaceDAO;
import com.holiday.home.dto.SearchPlaceDTO;
import com.holiday.home.models.Places;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    @Autowired
    PlaceDAO placeDAO;
    public Places createPlace(Places place){
        place.setId(null);
        placeDAO.save(place);
        return place;
    }

    public List<Places> getAllPlaces(){
        return placeDAO.findAll();
    }

    public List<Places> searchPlaces(SearchPlaceDTO searchPlaceDTO){
        LocalDate af = LocalDate.parse(searchPlaceDTO.getAvailableFrom());
        LocalDate at = LocalDate.parse(searchPlaceDTO.getAvailableTill());
        return this.getAllPlaces().stream().filter(place -> place.getAvailableFrom().minusDays(1).isBefore(af) &&
        place.getAvailableTill().plusDays(1).isAfter(at)).toList();
    }

}
