package org.example;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class Utilities {

    public Utilities(WebDriver driver, ExtentTest logger){

    }
/*
    public static void getEvidence(Status status, String msg){
        try {
            String screenshotPath = BasePage.getScreenshot(driver, "Sidebar");
            logger.log(status, msg, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }catch(Exception e){}
    }


 */
}
