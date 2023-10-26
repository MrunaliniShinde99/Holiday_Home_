package com.holiday.home.services;

import com.holiday.home.dao.UserDAO;
import com.holiday.home.dto.LoginResponseDTO;
import com.holiday.home.dto.RegisterRequestDTO;
import com.holiday.home.dto.RegisterResponseDTO;
import com.holiday.home.exceptions.AuthException;
import com.holiday.home.models.Customer;
import com.holiday.home.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    public LoginResponseDTO login(String email, String password){
        Customer user = userDAO.findUserByEmail(email).orElseThrow(AuthException::new);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        // Generating JWT token below based on user details
        loginResponseDTO.setToken(jwtUtil.generateToken(userDetails));
        loginResponseDTO.setUser(user);
        return loginResponseDTO;
    }

//    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO){
//
//    }

    public List<Customer> getAllUsers(){
        return userDAO.findAll();
    }
}
