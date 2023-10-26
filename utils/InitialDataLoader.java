package com.holiday.home.utils;

import com.holiday.home.dao.UserDAO;
import com.holiday.home.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitialDataLoader implements ApplicationRunner {

    @Autowired
    UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!userDAO.existsByRole("admin")){
            userDAO.save(new Customer(null, "John", "Doe", "admin@gmail.com", passwordEncoder.encode("12345678"), "ADMIN", new Date(), true));
        }
    
       
    }
}
