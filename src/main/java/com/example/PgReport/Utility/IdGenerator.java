package com.example.PgReport.Utility;

import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.UUID;

//From backend real

@Service
public class IdGenerator {

    @Transient
    private UUID uuid;

    public String getId() {
        this.uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

