package com.example.PgReport.User;

public interface UserBOService {

    boolean addUser(UserBO user);

    UserBO findByMobile(Long mobile);

    void addUserDetails(UserDetailsBO user);

    UserDetailsBO getUserDetails(Long mobile);



}
