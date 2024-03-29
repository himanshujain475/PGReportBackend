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

    private String salt;

    private Long mobile;

    private String pin;

    private boolean isActive;


    public UserBO() {

    }




}
