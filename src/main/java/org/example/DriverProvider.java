package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverProvider {

    public WebDriver getDriver(){
        Logger logger = LogManager.getLogger(DriverProvider.class);
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }

}
