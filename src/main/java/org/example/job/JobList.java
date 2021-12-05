package org.example.job;

import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Locale;

public class JobList extends Sidebar implements FilterTable{

    public static void filter(String status) throws InterruptedException {
        filter("", status);
    }

    public static void filter(String jobTitle, String status) throws InterruptedException {
        driver.findElement(By.xpath("//a[span[text()='Filter']]")).click();
        Thread.sleep(3000);
        if(!jobTitle.equals(""))
            driver.findElement(By.xpath("//input[@placeholder='Job Title']")).sendKeys(jobTitle);
        selectStatus(status);
        driver.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Apply']")).click();
    }

    public static void searchByJobTitleOrJobId(String jobTitle) throws InterruptedException {
        driver.findElement(By.xpath("//div[contains(@class,'search-head')]/input[@placeholder='Search by JOB TITLE or JOB ID.']")).sendKeys(jobTitle+ Keys.ENTER);
        Thread.sleep(9000);
    }

    /*
    public static void removeActiveFilters() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='clear-filter']")).click();
        Thread.sleep(2000);
    }*/

    public static void navigateToJobDetails(String jobTitle, String status){
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("JOB TITLE", jobTitle);
        data.put("STATUS", status.toLowerCase());
        FilterTable.navigateToFirstItemInList(data);
        //navigateToJobDetails(jobTitle, status, "");
    }

    public static void navigateToJobDetails() throws InterruptedException {
        //driver.findElement(By.xpath("(//table[contains(@class,'cloned')]/tbody/tr[vms-row[2]/td/span])[1]/vms-row[1]//p/a")).click();
        FilterTable.navigateToFirstItemInList();
        Thread.sleep(10000);
    }

    public static void navigateToJobDetails(String status){
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("STATUS", status.toLowerCase());
        FilterTable.navigateToFirstItemInList(data);
        //driver.findElement(By.xpath("(//table[contains(@class,'cloned')]/tbody/tr[vms-row[2]/td/span[contains(.,'"+status.toLowerCase()+"')]])[1]/vms-row[1]//p/a")).click();
    }

    public static void navigateToJobDetails(String jobTitle, String status, String jobId){
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("JOB TITLE", jobTitle);
        data.put("STATUS", status.toLowerCase());
        data.put("JOB ID", status.toLowerCase());
        FilterTable.navigateToFirstItemInList(data);
        //driver.findElement(By.xpath("(//table[contains(@class,'cloned')]/tbody/tr[vms-row[2]/td/span[contains(.,'"+status.toLowerCase()+"')] and not(vms-row[4]/td/span[contains(.,'--')])])[1]/vms-row[1]//p/a")).click();
    }

    private static void navigateFromDotMenu(String action) throws InterruptedException {
        // Navigate from dot Menu
    }

    public static void OptOutFromDotMenu(String optOutReason) throws InterruptedException {
        navigateFromDotMenu("Opt-Out");
        OptOutReason.optOut(optOutReason);
    }

    public static void OptInFromDotMenu() throws InterruptedException {
        navigateFromDotMenu("Opt-In");
        //Verify job is listed in All-Jobs list
    }

    public static void filterJobsWithTabs(String tabName) throws InterruptedException {
        driver.findElement(By.xpath("//ul[@class='tabs primary']/li[span[text()='"+tabName+"']]")).click();
        Thread.sleep(3000);
    }

    private static void selectStatus(String status) throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[div/label[normalize-space(text())='Status']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        //WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[label[normalize-space(text())='"+label+"']]])[1]/div[div/div[@class='ng-placeholder']/following-sibling::div/input]/span[contains(@class,'ng-arrow')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
        Thread.sleep(2000);
        dropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[text()='" + status + "']]")).click();
        Thread.sleep(2000);
    }

    public void verifyHighlightingOfTabs(String tabName){}

    public void moveColumnBetween(String colName, String leftCol, String rightCol){}
}
