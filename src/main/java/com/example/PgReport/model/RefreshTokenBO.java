package com.example.PgReport.model;

import com.example.PgReport.Utility.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Getter
@Setter
@Document(collection = "RefreshToken")
public class RefreshTokenBO {

    @Transient
    private static IdGenerator idg = new IdGenerator();

    @Id
    private String _id = idg.getId();

    private Long mobile;


    private String token;

    private Instant expiryDate;


}
