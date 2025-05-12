import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {

    public static String bearerToken;
    public static String id;
    Properties properties = new Properties();
    FileInputStream fis = new FileInputStream("C:\\Users\\r.hosthota\\Downloads\\untitled\\resources\\Properties.properties");

    public BaseTest() throws Exception {
        properties.load(fis);
        bearerToken = "Bearer" + properties.getProperty("token");
        id = "d1435ce2-55c9-4123-aa87-e43e980f0619";
    }
}
