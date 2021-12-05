package org.example.job;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.example.BasePage;
import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewJob extends Sidebar {

    public static void createJob() throws InterruptedException {
        //String templateName = "Vendor Representative";
        String templateName = "Automation Test Analyst";
        //String templateName = "CAU Vendor Manager";
        String jobManager = "Kirthika Muthukumaran";
        //String jobManager = "Connor HM";
        String startDt = "Today's date";
        String endDt = "Today's date";
        int minBillRate = (int) 1.00;
        int maxBillRate = (int) 2.00;
        String physicalWorkLocation = "Capgemini - Chennai - Chennai PCT - CapChennai600097";
        String firstName = "identifiedFirstName";
        String lastName = "identifiedLastName";
        String phone = "6567327875";
        String email = "varun.khurana+123654@simplifyvms.com";
        String vendorName = "1INSURER INC";
        String spendCategory = "Contracted Outsourced Services";
        String workLocation = "MSA-NY Syracuse Office";

        enterBasicInfo(templateName, jobManager, workLocation);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Continue']")).click();
        Thread.sleep(2000);
        enterFinancialDetails(startDt, endDt, minBillRate, maxBillRate);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Continue']")).click();
        Thread.sleep(2000);
        enterJobDetails(physicalWorkLocation, spendCategory, firstName, lastName, phone, email, vendorName);
        driver.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Submit']")).click();
        Thread.sleep(30000);
    }

    public static void enterBasicInfo(String templateName, String jobManager, String workLocation) throws InterruptedException {
        BasePage bp = new BasePage();

        Thread.sleep(12000);
        //driver.findElement(By.xpath("//div[label[normalize-space(text())='Job Template']]//input")).sendKeys(templateName);
        bp.enter("Job Template", By.xpath("//div[label[normalize-space(text())='Job Template']]//input"), templateName);

        //wait until //div[contains(@class,'dropdown-user')] is displayed
        Thread.sleep(9000);
        //driver.findElement(By.xpath("(//div[contains(@class,'dropdown-user')]/div/div[p[contains(@class,'job-title')][text()='"+templateName+"']])[1]")).click();

        bp.click("Auto Suggestion dropdown : "+templateName, By.xpath("(//div[contains(@class,'dropdown-user')]/div/div[p[contains(@class,'job-title')][text()='"+templateName+"']])[1]"),8);
        //wait until other fields get populated
        Thread.sleep(8000);
        selectDropdownData("Job Manager", jobManager);

        Thread.sleep(5000);
        //selectDropdownData("Work Location", workLocation);
        selectRandomDropdownData("Work Location", workLocation);
        getEvidence(Status.PASS, "Basic Info entered");
        /*
        try {
            String screenshotPath = BasePage.getScreenshot(driver, "Sidebar");
            logger.log(Status.PASS, "Basic Info entered", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }catch(Exception e){}

         */
    }

    public static void enterFinancialDetails(String startDt, String EndDt, int minBillRate, int maxBillRate) throws InterruptedException {
        //Select Start Date
        selectDate("Start Date",startDt);

        //select End Date
        selectDate("End Date",EndDt);

        //Enter Min Bill Rate
        WebElement objMinBillRate = driver.findElement(By.xpath("//input[@formcontrolname='min_bill_rate']"));
        objMinBillRate.click();
        objMinBillRate.sendKeys(String.valueOf(minBillRate));
        Thread.sleep(2000);

        //Enter Max Bill Rate
        WebElement objMaxBillRate = driver.findElement(By.xpath("//input[@formcontrolname='max_bill_rate']"));
        objMaxBillRate.click();
        objMaxBillRate.sendKeys(String.valueOf(maxBillRate));
        Thread.sleep(2000);


        getEvidence(Status.PASS, "Financial Info entered");
    }

    public static void enterJobDetails(String physicalWorkLocation, String spendCategory, String firstName, String lastName, String phone, String email, String vendorName) throws InterruptedException {
        Thread.sleep(2000);
        enterPreIdentifiedCandidateDetails(firstName, lastName, phone, email, vendorName);
        Thread.sleep(2000);
        enterFoundationalFields(physicalWorkLocation);
        Thread.sleep(2000);
        enterCustomFields(spendCategory);
    }

    private static void enterPreIdentifiedCandidateDetails(String firstName, String lastName, String phone, String email, String vendorName) throws InterruptedException {
        /*
        driver.findElement(By.xpath("//div[p[text()='Do you have a pre-identified candidate?']]/span")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//li[contains(@class,'add-cadidate')]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@formcontrolname='first_name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@formcontrolname='last_name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@formcontrolname='phone_number']")).sendKeys(phone);
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(email);
        //selectDropdownData("Select Vendor", vendorName);
        selectVendor("Select Vendor", vendorName);
        driver.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Add']")).click();
        //selectDropdownData("Physical Work Location", physicalWorkLocation);
        getEvidence(Status.PASS, "Pre-identified Candidate Added");
        */

        BasePage bp = new BasePage();
        bp.click(By.xpath("//div[p[text()='Do you have a pre-identified candidate?']]/span"),6);
        bp.click(By.xpath("//li[contains(@class,'add-cadidate')]"),4);
        bp.enter("First Name", By.xpath("//input[@formcontrolname='first_name']"), firstName);
        bp.enter("Last Name", By.xpath("//input[@formcontrolname='last_name']"), lastName);
        bp.enter("Phone Number", By.xpath("//input[@formcontrolname='phone_number']"), phone);
        bp.enter("Email", By.xpath("//input[@formcontrolname='email']"), email);
        selectVendor("Select Vendor", vendorName);
        bp.click("Add button", By.xpath("//button[@type='submit'][normalize-space(text())='Add']"),0);
        getEvidence(Status.PASS, "Pre-identified Candidate Added");
    }

    public static void selectVendor(String label, String data) throws InterruptedException {
        //WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[div/label[normalize-space(text())='"+label+"']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        //WebElement dropdown = driver.findElement(By.xpath("//ng-select[preceding-sibling::label[normalize-space(text())='"+label+"']]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));

            WebElement dropdown = driver.findElement(By.xpath("//ng-select[preceding-sibling::label[contains(text(),'" + label + "')]]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: \"center\"});", dropdown);
            Thread.sleep(2000);
            dropdown.click();
            Thread.sleep(2000);
            dropdown.sendKeys(data);
            Thread.sleep(8000);
            driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[normalize-space(text())='" + data + "']]")).click();
            Thread.sleep(2000);
            getEvidence(Status.PASS, "Vendor entered");
            /*
            try {
                String screenshotPath = BasePage.getScreenshot(driver, "Sidebar");
                logger.log(Status.PASS, "Vendor entered", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }catch(Exception e){}

             */
    }

    private static void enterFoundationalFields(String physicalWorkLocation) throws InterruptedException {
        //selectDropdownData("Physical Work Location", physicalWorkLocation);
        selectRandomDropdownData("Physical Work Location", physicalWorkLocation);
    }

    private static void enterCustomFields(String spendCategory) throws InterruptedException {
        selectDropdownData("Spend Category", spendCategory);
    }

    /*
    private static void selectDropdownData(String label, String data) throws InterruptedException {
        //WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[div/label[normalize-space(text())='"+label+"']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        WebElement dropdown = driver.findElement(By.xpath("//ng-select[preceding-sibling::label[normalize-space(text())='"+label+"']]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
        Thread.sleep(2000);
        dropdown.click();
        Thread.sleep(2000);
        dropdown.sendKeys(data);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[normalize-space(text())='" + data + "']]")).click();
        Thread.sleep(2000);
    }
    */


    private static void selectDate(String dtLabel, String dt) throws InterruptedException {
        String formcontrolname = null;
        if(dtLabel.equals("Start Date"))
            formcontrolname = "start_date";
        if(dtLabel.equals("End Date"))
            formcontrolname = "end_date";
        driver.findElement(By.xpath("//svms-datepicker[@formcontrolname='"+formcontrolname+"']//div/input")).click();
        Thread.sleep(1500);
        // To select today's date
        if(dt.equals("Today's date")) {
            //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day -current')]")).click();
            try{
                driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'current')]")).click();
                //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day -current')]")).click();
            }catch(Exception e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                System.out.println(formatter.format(LocalDate.now()));
                selectDate(formatter.format(LocalDate.now()));
            }
        }else{
            //TODO
        }
        Thread.sleep(2000);
    }

}
