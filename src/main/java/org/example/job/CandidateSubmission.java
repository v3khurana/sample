package org.example.job;

import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class CandidateSubmission extends Sidebar {

    //public static void enterCandidateSubmissionDetails(List<Map<String, String>> data) throws InterruptedException {
    public static void enterCandidateSubmissionDetails() throws InterruptedException {
        //String availableStartDt = data.get(0).get("Available Start Date");
        //String clientBillRate = data.get(0).get("Client Bill Rate");

        String availableStartDt = "Today's Date";
        String clientBillRate = "2";

        selectCurrentDate("//svms-datepicker[@formcontrolname='availableStartDate']");
        Thread.sleep(2000);
        selectDate("//svms-datepicker[@formcontrolname='availableEndDate']","18/12/2021");
        driver.findElement(By.xpath("//input[@formcontrolname='billRatevalue']")).clear();
        driver.findElement(By.xpath("//input[@formcontrolname='billRatevalue']")).sendKeys(clientBillRate);
        Thread.sleep(2000);
        selectField("values", "D");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space(text())='Submit Candidate']")).click();
        Thread.sleep(14000);
    }

    private static void selectField(String formControlName, String value) throws InterruptedException {
        driver.findElement(By.xpath("//ng-select[@formcontrolname='"+formControlName+"']//input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ng-select[@formcontrolname='"+formControlName+"']//ng-dropdown-panel//div[@role and span[contains(.,'"+value+"')]]")).click();
        Thread.sleep(2000);
    }

    private static void selectCurrentDate(String sectionElemXpath) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath(sectionElemXpath + "//div[contains(@class,'datepicker')]/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'daterangepicker-wrapper')]//div[contains(@class,'current')]")).click();
    }

    private static void selectDate(String sectionElemXpath, String dt) throws InterruptedException {
        WebElement objDateSelector = driver.findElement(By.xpath(sectionElemXpath + "//div[contains(@class,'datepicker')]/input"));
        objDateSelector.click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",objDateSelector);
        Thread.sleep(2000);
        selectDate(dt);
    }
}
