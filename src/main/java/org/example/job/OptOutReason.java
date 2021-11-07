package org.example.job;

import org.example.BasePage;
import org.example.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class OptOutReason extends BasePage {

    public static void optOut() throws InterruptedException {
        optOut("", "");
    }

    public static void optOut(String reason) throws InterruptedException {
        optOut(reason, "");
    }

    public static void optOut(String reason, String notes) throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[div/label[normalize-space(text())='Reason for Opt-Out']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        //WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[label[normalize-space(text())='"+label+"']]])[1]/div[div/div[@class='ng-placeholder']/following-sibling::div/input]/span[contains(@class,'ng-arrow')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
        Thread.sleep(2000);
        dropdown.click();
        Thread.sleep(2000);
        if(reason == "")
            driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span)][1]")).click();
        else
            driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span)[text()='" + reason + "']]")).click();
        Thread.sleep(2000);
        if(notes != "")
            driver.findElement(By.xpath("//textarea[@formcontrolname='notes']")).sendKeys(notes);
        driver.findElement(By.xpath("//button[normalize-space(text())='Opt-Out']")).click();
        Thread.sleep(4000);

        // Verify job is not displayed in View-All Jobs
        // Verify job is displayed under Opted-Out jobs list

    }

}
