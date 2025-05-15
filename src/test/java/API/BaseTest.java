package API;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {

    public static String bearerToken;
    public static String id;
    public static String contentType;
    Properties properties = new Properties();
    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\Properties.properties");

    public BaseTest() throws Exception {
        properties.load(fis);
        bearerToken = "Bearer" + properties.getProperty("token");
        id = "9d9f0422-778f-42b5-bb83-48dbc848a0da";
        contentType = "application/json";
    }

    @BeforeClass
    public void setBaseURI(){
        RestAssured.baseURI = "http://localhost:9000";
    }
}
