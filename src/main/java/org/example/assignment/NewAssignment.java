package org.example.assignment;

import org.example.Sidebar;
import org.example.candidate.NewCandidate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class NewAssignment extends Sidebar {

    public static void createQuickAssignment() throws InterruptedException {
        String vendorName = "Aerotek Vendor";
        String sourceType = "New Worker";
        String emailAddr = "abcd@simplifyvms.com";
        String candidateName = " Hiral  Harita (soumyashree+7777777@simplifyvms.com)";
        String tenureStartDt = "Today's date";

        enterCandidateInfo(candidateName);
        enterVendorInfo(vendorName);
        enterWorkerInfo(sourceType, tenureStartDt, emailAddr);
        driver.findElement(By.xpath("//div[contains(@class,'form-footer')]//button[normalize-space(text())='Continue']")).click();
    }

    public static void enterCandidateInfo(String candidateName) throws InterruptedException {
        selectData("Select Candidate", candidateName);
    }

    public static void enterVendorInfo(String vendorName) throws InterruptedException {
        selectData("Select Vendor", vendorName);
    }

    public static void enterWorkerInfo(String sourceType, String tenureStartDt, String emailAddr) throws InterruptedException {
        selectData("Worker Source Type", sourceType);
        driver.findElement(By.xpath("//svms-datepicker[@formcontrolname='original_start_date']/div/input")).click();
        // To select today's date
        if(tenureStartDt.equals("Today's date"))
            driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day -current')]")).click();
        else{
            //TODO
        }
        driver.findElement(By.xpath("//input[@formcontrolname='official_email']")).sendKeys(emailAddr);
    }

    public void createCandidate(String firstName, String lastName, String emailAddr){
        driver.findElement(By.xpath("//div[contains(@class,'form-group')]//button[normalize-space(text())='Create Candidate']")).click();
        //NewCandidate.enterCandidateInfo(firstName, lastName, emailAddr);
    }

    private static void selectData(String label, String data) throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[div/label[normalize-space(text())='"+label+"']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
        Thread.sleep(2000);
        dropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[text()='" + data + "']]")).click();
        Thread.sleep(2000);
    }
}
