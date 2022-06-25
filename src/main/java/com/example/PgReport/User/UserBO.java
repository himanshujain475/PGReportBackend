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
@Document(collection = "User")

public class UserBO {
    @Transient
    private static IdGenerator idg = new IdGenerator();

    @Id
    private String _id = idg.getId();

    private String name;

    private String userName;

    private String password;
    private String roomNo;

    private AddressBO address;

    private String occupation;

    private String mobile;

    private String joiningDate;

    private String securityAmount;



    public UserBO() {

    }

    public UserBO(String name, String roomNo) {
        this.name = name;
        this.roomNo = roomNo;
    }


}
