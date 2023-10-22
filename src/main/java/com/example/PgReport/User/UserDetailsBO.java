package com.example.PgReport.User;


import com.example.PgReport.Utility.IdGenerator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "user_details")
public class UserDetailsBO {

    @Transient
    private static IdGenerator idg = new IdGenerator();

    @Id
    private String _id = idg.getId();

    private String name;

    private String fatherName;

    private String motherName;

    private String city;

    private String state;

    private String zip;


    private Long mobile;

    private int roomNo;

    private String occupancy;

    private String address;

    private String occupation;


    private String joiningDate;

    private double securityAmount;

    private boolean isActive;



    public UserDetailsBO() {

    }
}
