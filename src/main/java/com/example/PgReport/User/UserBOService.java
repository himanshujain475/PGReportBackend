package com.example.PgReport.User;

public interface UserBOService {

    boolean addUser(UserBO user);

    UserBO findByUserName(String user);
}
