package com.example.PgReport.User;

public interface UserBOService {

    void addUser(UserBO user);

    UserBO findByUserName(String user);
}
