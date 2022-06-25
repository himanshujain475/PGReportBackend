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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBO userBO = userService.findByUserName(username);
        if(userBO == null) return  null;

        String userName = userBO.getUserName();
        String password =  userBO.getPassword();
        return new User(userName,password,new ArrayList<>());
    }


}
