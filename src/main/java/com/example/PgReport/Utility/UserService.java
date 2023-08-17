package com.example.PgReport.Utility;

import com.example.PgReport.User.UserBO;
import com.example.PgReport.User.UserBOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserBOService userService;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        UserBO userBO = userService.findByMobile(Integer.parseInt(mobile));
        if(userBO == null) return  null;

        String userName = userBO.getUserName();
        int  pin =  userBO.getPin();
        //int mobile = userBO.getMobile();
        return new User(Integer.toString(userBO.getMobile()),Integer.toString(pin),new ArrayList<>());
    }



}
