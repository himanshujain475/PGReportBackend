package com.example.PgReport.User;

public interface UserRepo {
    void addUser(UserBO user);

    public UserBO findByMobile(int mobile);
}
