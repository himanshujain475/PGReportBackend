package com.example.PgReport.User;

import org.apache.catalina.User;

public interface UserRepo {
    void addUser(UserBO user);

    public UserBO findByMobile(Long mobile);

    void addUserDetails(UserDetailsBO userDetailsBO);

    UserDetailsBO getUserDetails(Long mobile);
}
