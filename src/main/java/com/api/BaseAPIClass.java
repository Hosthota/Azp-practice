package com.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPIClass {


    protected static Response response = null;
    protected static RequestSpecification requestSpecification;

       public static String getResponseBody() {
        return response != null ? response.getBody().asString() : "No response body";
    }

    public static String getRequestBody(){
           return requestSpecification.log().all() != null ? requestSpecification.log().all().toString() : "No Request Body";
    }
}
