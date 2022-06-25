package com.example.PgReport.commonFunction;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvertorClass {

    public static  <T> T objectMapper(Object object,Class<T> convertedClassName){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        T convertedObject = mapper.convertValue(object, convertedClassName);
        return convertedObject;
    }

}
