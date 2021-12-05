package org.example.job;

import org.example.BasePage;
import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class NewOffer extends Sidebar {

    public static void clickCreateOfferBtn() throws InterruptedException {
        driver.findElement(By.xpath("//button[normalize-space(text())='Create offer']")).click();
        Thread.sleep(5000);
    }

    public static void createNewOffer() throws InterruptedException {
        enterOfferDetails();
        enterCustomFields();
        //Click Release offer button
        driver.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Release Offer']")).click();
        Thread.sleep(14000);
    }

    public static void enterOfferDetails() throws InterruptedException {
        String startDt = "Today's date";
        String endDt = "30/12/2021";
        String timeSheetType = "TITO";
        String assignmentActiveUpon = "Offer Acceptance";
        String origStartDt = "Today's date";

        Thread.sleep(6000);
        selectDate("Start Date", startDt);
        Thread.sleep(2000);
        selectDate("End Date", endDt);
        Thread.sleep(2000);
        selectDropdownData("Timesheet Type", timeSheetType);
        Thread.sleep(2000);
        selectDropdownData("Assignment Active Upon", assignmentActiveUpon);
        Thread.sleep(2000);
        selectDate("Worker Original/Tenure Start Date", origStartDt);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@formcontrolname='worker_email']")).clear();
        driver.findElement(By.xpath("//input[@formcontrolname='worker_email']")).sendKeys("varun.khurana+13563@simplifyvms.com");
        Thread.sleep(2000);
    }

    public static void enterCustomFields() throws InterruptedException {
        String modelUserIdAfter = "modelUserID";
        String workerPhoneReq = "Skype Phone";
        String workerPCVMConfig = "Macbook";
        String workerDeskLocation = "desk_03A";
        String spendCategory = "Contract Programmers - Fixed Bid";

        enterTextInCustomField("Model User ID After", modelUserIdAfter);
        selectDropdownData("Worker Phone Requirements", workerPhoneReq);
        selectDropdownData("Worker PC/VM Configuration", workerPCVMConfig);
        enterTextInCustomField("Work Desk Location", workerDeskLocation);
        selectDropdownData("Spend Category", spendCategory);
    }

    private static void selectDate(String label, String dt) throws InterruptedException {
        String formcontrolname = null;
        if(label.equals("Start Date"))
            formcontrolname = "startDate";
        if(label.equals("End Date"))
            formcontrolname = "endDate";
        if(label.equals("Worker Original/Tenure Start Date"))
            formcontrolname = "tenure_date";

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement objDt = driver.findElement(By.xpath("//svms-datepicker[@formcontrolname='"+formcontrolname+"']//div/input"));
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",objDt);
        //js.executeScript("arguments[0].scrollIntoView({block: \"top\"});",objDt);
        Thread.sleep(4000);
        objDt.click();
        if(dt.equals("Today's date")) {
            //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day -current')]")).click();
            try {
                driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'current')]")).click();
                //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day -current')]")).click();
            } catch (Exception e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                System.out.println(formatter.format(LocalDate.now()));
                selectDate(formatter.format(LocalDate.now()));
            }
        }else
            selectDate(dt);
    }
}
