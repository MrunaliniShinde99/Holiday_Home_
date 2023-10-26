package com.holiday.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPlaceDTO  {
    public MultipartFile image;
    private String name;
    private String touristAttraction;
    private String description;
    private String address;
    private String amenities;
    private String availableFrom;
    private String availableTill;
}
