package com.holiday.home.dto;


import com.holiday.home.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private Customer user;
   
}
