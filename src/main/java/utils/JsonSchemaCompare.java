package utils;


import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.io.FileInputStream;


public class JsonSchemaCompare {

    public void compareResponseWithJsonSchema(Response response, String fileName){
        try{
            FileInputStream fis =  new FileInputStream(System.getProperty("user.dir") + "\\resources\\JSON Schema\\"+ fileName);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(fis));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
