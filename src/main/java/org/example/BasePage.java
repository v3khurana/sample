package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public static WebDriver driver = null;

    public WebDriver initializeDriver(){
        DriverProvider dp = new DriverProvider();
        driver = dp.getDriver();
        return driver;
    }

    public void click(By byObj){
        click(byObj, 0);
    }

    public void click(By byObj, int waitInSec){
        int waitTime = 0;
        if(waitInSec == 0)
            waitTime = 1500;
        else
            waitTime = waitInSec*1000;
        driver.findElement(byObj).click();
        try {
            Thread.sleep(waitTime);
        }catch(InterruptedException e){}
    }

    public void enter(By byObj, String data){
        driver.findElement(byObj).sendKeys(data);
        try {
            Thread.sleep(1500);
        }catch(InterruptedException e){}
    }
}
