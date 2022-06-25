package com.example.PgReport.User;

public interface UserRepo {
    void addUser(UserBO user);

    public UserBO findByUserName(String userName);
}
