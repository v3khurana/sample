package org.example.job;

import org.example.SidePage;
import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class InterviewDetails extends Sidebar {
    /*
        DO NOT CHANGE BELOW CODE
     */
    public static void markAsComplete(String reason) throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Mark as Complete']")).click();
        Thread.sleep(5000);

        //Select Reason
        WebElement objInput = driver.findElement(By.xpath("//svms-sidebar-body//input"));
        actionClick(driver, objInput);
        WebElement objValue = driver.findElement(By.xpath("//div[contains(@class,'ng-option')]/span[normalize-space(text())='"+reason+"']"));
        actionClick(driver, objValue);

        //Click Submit button
        SidePage.accept(driver.findElement(By.xpath("//app-mark-as-comp-side-panel")),"Submit");
        Thread.sleep(6000);
    }

    private static void actionClick(WebDriver driver, WebElement element) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        Thread.sleep(3000);
    }

    public static void createOffer() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Create Offer']")).click();
        NewOffer.createNewOffer();
    }

}
