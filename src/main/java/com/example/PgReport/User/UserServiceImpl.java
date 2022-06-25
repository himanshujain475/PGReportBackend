package com.example.PgReport.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserBOService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public void addUser(UserBO user) {
        userRepo.addUser(user);
    }
    @Override
    public UserBO findByUserName(String userName){
       return userRepo.findByUserName(userName);
    }
}
