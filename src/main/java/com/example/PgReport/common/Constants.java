package com.example.PgReport.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constants {

    @Value("${saltKey}")
    public static String salt;
}
