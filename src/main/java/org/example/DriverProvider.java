package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverProvider {

    public WebDriver getDriver(){
        Logger logger = LogManager.getLogger(DriverProvider.class);
        WebDriverManager.chromedriver().setup();
        //ChromeDriver driver = new ChromeDriver();
        //System.setProperty("webdriver.chrome.driver","C:/Users/VarunKhurana/Downloads/chromedriver.exe");
        ChromeDriver driver = null;
        //RemoteWebDriver driver = null;
        try {
            /*
             driver = new RemoteWebDriver(

                    new URL("http://127.0.0.1:9515"),

                    new ChromeOptions());

             */
             //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
             //driver = new RemoteWebDriver(new URL("http://10.10.1.150:4444/wd/hub"), new ChromeOptions());
             driver = new ChromeDriver();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return driver;
    }

}
