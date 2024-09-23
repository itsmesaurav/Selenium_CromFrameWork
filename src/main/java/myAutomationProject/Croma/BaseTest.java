package myAutomationProject.Croma;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
    public WebDriver driver;
    public Properties prop;
    public Logger logger = LogManager.getLogger(BaseTest.class);

    public void initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\user\\eclipse-workspace\\Croma\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);

       // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Driver initialized successfully.");
    }

    public void launchApplication() {
        driver.get(prop.getProperty("url"));
        logger.info("Launched application: " + prop.getProperty("url"));
    }
}
