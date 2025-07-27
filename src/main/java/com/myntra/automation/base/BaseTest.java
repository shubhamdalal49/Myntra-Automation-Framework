package com.myntra.automation.base;

// import io.github.bonigarcia.wdm.WebDriverManager; // <- Is line ko hata diya gaya hai

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration; // <- Naya import add kiya gaya hai
import java.util.Properties;
// import java.util.concurrent.TimeUnit; // <- Puraana import hata diya gaya hai

public class BaseTest {

    public static WebDriver driver;
    public Properties prop;

    public BaseTest() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("./src/main/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browserName = System.getProperty("browser", "chrome");

        if (browserName.equalsIgnoreCase("chrome")) {
            // WebDriverManager.chromedriver().setup(); // <- Is line ko hata diya gaya hai
            driver = new ChromeDriver(); // Selenium Manager ab driver ko khud handle karega
        } else if (browserName.equalsIgnoreCase("firefox")) {
            // WebDriverManager.firefoxdriver().setup(); // <- Is line ko hata diya gaya hai
            driver = new FirefoxDriver(); // Selenium Manager ab driver ko khud handle karega
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        // Timeouts ko naye tareeke se likha gaya hai (Duration)
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.myntra.com/");
    }

    // Humne yahan se @AfterMethod hata diya hai kyunki hum Cucumber ka @After hook
    // MyntraStepDefinitions.java file mein use kar rahe hain. Isse confusion nahi hoga.
}
