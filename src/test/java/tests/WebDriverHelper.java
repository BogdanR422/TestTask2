package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverHelper {
    public static WebDriver getDriver(String browser) throws Exception {
        WebDriver driver = null;

        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("path.chromedriver"));
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("path.geckodriver"));
            driver = new FirefoxDriver();
        } else {
            throw new Exception("Wrong browser...");
        }

        return  driver;
    }

}