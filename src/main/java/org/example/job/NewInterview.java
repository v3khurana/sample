package org.example.job;

import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class NewInterview extends Sidebar {

    public static void scheduleInterview(String interviewType, String location, String duration, String interviewDate, String timeSlots) throws InterruptedException {
        Thread.sleep(15000);
        selectDropdownData("Interview Type", interviewType);
        driver.findElement(By.xpath("//input[@formcontrolname='location']")).sendKeys(location);
        selectDropdownData("Select Interview", duration);
        driver.findElement(By.xpath("//div[img[@id='datepicker']]/input")).click();
        Thread.sleep(1500);
        selectDate(interviewDate);
        Thread.sleep(8000);
        //selectDropdownData("TIME SLOTS(S)",timeSlots);
        // Select Time Slots
        WebElement dropdown = driver.findElement(By.xpath("//ng-select[@placeholder='Time Slots']/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
        Thread.sleep(2000);
        dropdown.click();
        Thread.sleep(2000);
        dropdown.sendKeys(timeSlots);
        Thread.sleep(5000);
        //driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[normalize-space(text())='" + timeSlots + "']]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'ng-option')]/div/button[normalize-space(text())='"+timeSlots+"']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[normalize-space(text())='Schedule']")).click();
        Thread.sleep(8000);
    }

}
