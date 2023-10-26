package com.holiday.home.controllers;

import com.holiday.home.dao.UserDAO;
import com.holiday.home.dto.*;
import com.holiday.home.models.Customer;
import com.holiday.home.services.AuthService;
import com.holiday.home.services.UserDetailsService;
import com.holiday.home.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    UserDAO userDAO;

    @Autowired
    AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<BasicResponseDTO<RegisterResponseDTO>> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        BasicResponseDTO<RegisterResponseDTO> basicResponseDTO = new BasicResponseDTO<>();
        basicResponseDTO.setData(null);
        basicResponseDTO.setSuccess(false);
        if( !registerRequestDTO.getPassword().equals(registerRequestDTO.getConfirmPassword()) ){
            basicResponseDTO.setMessage("Password and confirm password not matched");
            return new ResponseEntity<>(basicResponseDTO, HttpStatus.BAD_REQUEST);
        }
        if(userDAO.existsByEmail(registerRequestDTO.getEmail())){
            basicResponseDTO.setMessage("User already exists");
            return new ResponseEntity<>(basicResponseDTO, HttpStatus.CONFLICT);
        }
        Customer user = new Customer();
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setLastName(registerRequestDTO.getLastName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setRole(registerRequestDTO.getRole());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setCreatedOn(new Date());
        userDAO.save(user);
        log.info("USER REGISTRATION SUCCESSFUL");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        basicResponseDTO.setData(new RegisterResponseDTO(jwtUtil.generateToken(userDetails), user.getEmail(), user.getFirstName()));
        basicResponseDTO.setSuccess(true);
        return new ResponseEntity<>(basicResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO , HttpServletRequest request){
        log.info("USER AUTHENTICATION REQUEST CAME");
        LoginResponseDTO result = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        log.info("USER AUTHENTICATION SUCCESSFULLY");
        request.getSession().setAttribute("role", result.getUser().getRole());
        return new ResponseEntity<>( new BasicResponseDTO<>(true, "Authenticated", result),HttpStatus.OK );
    }
}
