package org.example;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.HashMap;

public class AccountSetup extends BasePage{

    public static void setup(String url, String password) throws InterruptedException {
        //driver.get("http://url7563.simplifyvms.com/ls/click?upn=5HkliqwhwP0HVPbCvDBYSM58jKhycALmDCrOqI8BoH0VSiGxYRX0rqDW8qIIePiGjzgYrYNJC-2BZ-2B00m3zTXi-2B5h3H3Mwdg7GR1XGIQg-2BBMeoGBXnEjUgSWmhYuA1-2FcqzR7KY63giBZnH-2F277XMTfVY1sVMpIFBO26wdjF1l2UM9jGP0D1evn5xW-2B1XZbw1Tf1HrCJkRpMacQGDAmM0jCQOku0-2B4GWw9gC6HfSeBaQtQ-3DPmfx_4RefGLBzKwWXYqoT-2B1aK0RCK5iMeChUvxXGgBB0jUQHDCUU8ZUchtb-2B7j8y68L2dpG0BPQWWCMJAJDnykD1CmKUw-2FbHEH2NhOjPiBJc5FR8taw7nALTgzyCZSF5JMM8dPTfCx-2F4d26Htwl9Ey-2BThi3SvzS9aDnJQGgr5x-2F34paowRw4TXKIwN6VvBK7sHrQSOHRy1JEks4aYuDEyNjSqvA-3D-3D");
        driver.get(url);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[contains(@class,'account-btn')][contains(text(),concat('Let',\"'\",'s do it!'))]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(password);
        driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(password);
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[contains(@class,'account-btn')][contains(text(),'Save & Continue')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@class,'account-btn')][contains(text(),'Save & Continue')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@class,'account-btn')][contains(text(),'Save & Continue')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@class,'account-btn')][contains(text(),'Save & Continue')]")).click();
        Thread.sleep(5000);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://preprod-app.simplifyvms.com/dashboard"));
    }

    public static void createTimeSheet(String assignment, String workPeriod, HashMap<String, String[]> timeSheetData) throws InterruptedException {
        driver.findElement(By.xpath("//app-create-timesheet/div")).click();
        Thread.sleep(10000);
        SidePage.selectDropdownData("Select Assignment", assignment);
        Thread.sleep(2000);
        SidePage.selectDropdownData("Select Work Period", workPeriod);
        Thread.sleep(2000);
        SidePage.accept(driver.findElement(By.xpath("//app-enter-timesheet")),"Continue");
        Thread.sleep(8000);
        int startDt = Integer.parseInt(workPeriod.split("-")[0].trim().split("/")[1]);
        int endDt = Integer.parseInt(workPeriod.split("-")[1].trim().split("/")[1]);
        for(int i = startDt; i <= endDt; i++){
            String timeIn = timeSheetData.get(String.valueOf(i))[0];
            String timeOut = timeSheetData.get(String.valueOf(i))[1];
            driver.findElement(By.xpath("//div[@class='day-col']/div[2][span[normalize-space(text())='"+String.valueOf(i)+"']]/following-sibling::div[1]/div/input")).sendKeys(timeIn);
            driver.findElement(By.xpath("//div[@class='day-col']/div[2][span[normalize-space(text())='"+String.valueOf(i)+"']]/following-sibling::div[2]/div/input")).sendKeys(timeOut);
        }
        //Thread.sleep(4000);
        //driver.findElement(By.xpath("//button[text()='Submit Timesheet']")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//button[text()='Submit Timesheet']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(@class,'modal-footer')]/button[text()='Yes']")).click();
        Thread.sleep(8000);
    }
}
