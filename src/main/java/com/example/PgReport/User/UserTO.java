package com.example.PgReport.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserTO {

    private String name;
    private String state;
    private String city;
    private String zip;
    private String occupation;
    private String fatherName;
    private String motherName;
    private String joiningDate;
    private String address;
    private double securityAmount;
    private int roomNo;
    private Long mobile;
    private String occupancy;

}
