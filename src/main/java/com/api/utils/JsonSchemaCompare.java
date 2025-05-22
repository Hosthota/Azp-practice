package com.api.utils;


import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;


public class JsonSchemaCompare {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    public void compareResponseWithJsonSchema(Response response, String fileName){
        try{
            FileInputStream fis =  new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//JSON Schema//"+ fileName);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(fis));
            logger.info("Response body structure matches with JSON Schema provided");
        } catch (Exception e) {
            logger.error("Response body structure does not matches with JSON Schema provided");
            throw new RuntimeException(e);
        }
    }
}
