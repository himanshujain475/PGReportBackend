package com.example.PgReport.common;

import lombok.Data;

@Data
public class ResponseBody {

    private String status;

    private int code;

    private Object returnBody;

    private String description;

    public ResponseBody(int code,String status,String description,Object returnBody){
        this.code = code;
        this.status = status;
        this.description = description;
        this.returnBody= returnBody;
    }

}
