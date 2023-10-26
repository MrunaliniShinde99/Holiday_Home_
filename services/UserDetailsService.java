package com.holiday.home.services;


import com.holiday.home.dao.UserDAO;
import com.holiday.home.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("userService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> user = userDAO.findUserByEmail(email);
        if(user.isPresent()) {
            Customer _user = user.get();
            return  new org.springframework.security.core.userdetails.User(_user.getEmail(), _user.getPassword(), new ArrayList<>());
        }
        return null;
    }


}
