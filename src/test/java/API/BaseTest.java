package API;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {

    public static String bearerToken;
    public static String id;
    public static String contentType;
    Properties properties = new Properties();
    FileInputStream fis;
    public Logger log;

    public BaseTest(){
        try{
            fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\Properties.properties");
            properties.load(fis);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        bearerToken = "Bearer" + properties.getProperty("token");
        id = "d90f60d1-7c93-4182-8e60-a21ffa203679";
        contentType = "application/json";
        log = Logger.getLogger(this.getClass().getName());
    }

    @BeforeClass
    public void setBaseURI(){
        RestAssured.baseURI = "http://localhost:9000";
        log.info("Set Base URL completed");
    }
}
