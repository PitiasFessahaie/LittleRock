package com.bdd.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Properties;

public class DriverInitializer {

    private static Properties properties = null;
    public static WebDriver driver;

    static {
        try {
            properties = new Properties();
            properties.load(DriverInitializer.class.getClassLoader()
                    .getResourceAsStream("application.properties"));
            WebDriverManager.chromedriver().setup();
            WebDriverManager.firefoxdriver().setup();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver startDriver() {
        
        switch (getProperty("browser")) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver getDriver(){
    	return driver;
    }

    public static String getProperty(String key) {
        return properties == null ? null : properties.getProperty(key, "");
    }
}
