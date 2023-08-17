package com.example.PgReport.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserBOService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public boolean addUser(UserBO user) {
        UserBO userBO =userRepo.findByMobile(user.getMobile());
        if(userBO == null){
            userRepo.addUser(user);
            return true;
        }
        else{
            return false;
        }

    }
    @Override
    public UserBO findByMobile(int mobile){
       return userRepo.findByMobile(mobile);
    }
}
