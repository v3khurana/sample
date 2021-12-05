package org.example.job;

import org.example.SidePage;
import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

public class JobDetails extends Sidebar {

    public static void navigateTabs(String tabName) throws InterruptedException {
        try {
            driver.findElement(By.xpath("//ul[@class='tabs primary']/li/a[normalize-space(text())='" + tabName + "']")).click();
        }catch(Exception noTab){
            driver.findElement(By.xpath("//ul[@class='tabs primary']/li/a[normalize-space(text())='More']")).click();
            Thread.sleep(3000);
            try{
                //driver.findElement(By.xpath("//ul[@class='tabs primary']/li/[normalize-space(text())='" + tabName + "']")).click();
                driver.findElement(By.xpath("//ul[@class='secondary']/li[(a|div/span)[normalize-space(text())='"+tabName+"']]")).click();
            }catch (Exception noTabInMore){
                System.out.println("Tab named "+ tabName + " does not exists");
            }
        }
        if(tabName.equals("Available Candidates")|tabName.equals("Interviews"))
            Thread.sleep(25000);
        else
            Thread.sleep(18000);
    }

    public static String getJobDescription(){
        return "";
    }

    public static HashMap<String, String> getFoundationalData(){
        HashMap<String, String> fnData = new HashMap<String, String>();
        return fnData;
    }

    public static HashMap<String, String> getCustomFields(){
        HashMap<String, String> custData = new HashMap<String, String>();
        return custData;
    }

    public static HashMap<String, String> getBasicInfo(){
        HashMap<String, String> basicInfo = new HashMap<String, String>();
        return basicInfo;
    }

    public static HashMap<String, String> getFinancialInfo(){
        HashMap<String, String> financialInfo = new HashMap<String, String>();
        return financialInfo;
    }

    public static String getJobStatus(){
        return driver.findElement(By.xpath("//header//span[contains(@class,'status')]")).getText().trim();
    }

    public static String getJobId(){
        WebElement elem = driver.findElement(By.cssSelector("header > div:nth-child(1) > p"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return arguments[0].childNodes[1].textContent", elem).toString().trim();
    }

    public static String getJobManager(){
        /*
        try {
            WebElement objJobManager = driver.findElement(By.xpath("//div[div/p[normalize-space(text())='Job Manager']]/following-sibling::div//app-approval-member-profile//div[1]/span"));
            return objJobManager.getText().trim();
        }catch(Exception e){
            System.out.println("Unable to find job manager");
            return null;
        }*/
        return driver.findElement(By.xpath("//div[div/p[contains(normalize-space(text()),'Job Manager')]]/following-sibling::div//app-approval-member-profile//div[1]/span")).getText().trim();
    }

    public static String getHiringManager(){
        //return driver.findElement(By.xpath("//div[div/p[contains(normalize-space(text()),'Hiring Manager')]]/following-sibling::div//app-approval-member-profile//div[1]/span")).getText().trim();
        return driver.findElement(By.xpath("//div[div/p[contains(normalize-space(text()),'Hiring Manager') or contains(normalize-space(text()),'Client Administrator')]]/following-sibling::div//app-approval-member-profile//div[1]/span")).getText().trim();
    }

    public static void distributeJob(String...vendors) throws InterruptedException {
        for(String vendor : vendors)
            addVendor(vendor);
        //Select No submission limit
        driver.findElement(By.xpath("//div[span[text()='Enable Submission limit']]/span[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space(text())='Distribute']")).click();
        Thread.sleep(20000);
        driver.navigate().refresh();
        Thread.sleep(6000);
    }

    private static void addVendor(String vendor) throws InterruptedException {
        List<WebElement> objSelectedVendors = driver.findElements(By.xpath("//div[contains(@class,'selected-vendor')]"));
        if(objSelectedVendors.size()==0 || !driver.findElement(By.xpath("//div[contains(@class,'selected-vendor')][span[normalize-space(text())='"+vendor+"']]")).isDisplayed()){
            vendorSearch(vendor);
        }
    }

    private static void vendorSearch(String vendorNm) throws InterruptedException {
        try {
            driver.findElement(By.xpath("//input[@placeholder='Search for Distribution Recipients']")).sendKeys(vendorNm);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//div[@class='autocomplete-items']/div[normalize-space(text())='" + vendorNm + "']")).click();
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println("Vendor Search not working, proceeding with advanced search");
            vendorsAdvanceSearch(vendorNm);
        }
    }

    private static void vendorsAdvanceSearch(String vendorNm) throws InterruptedException {
        driver.findElement(By.xpath("//button[@type='submit'][span[text()='Advance Search']]")).click();
        WebElement objSideBar = driver.findElement(By.xpath("//app-distribution-sidebar"));
        Thread.sleep(5000);
        objSideBar.findElement(By.xpath(".//input[@placeholder='Filter Vendor by Name']")).sendKeys(vendorNm);
        objSideBar.findElement(By.xpath("//button[text()='Filter']")).click();
        Thread.sleep(10000);
        List<WebElement> objCheckbox = driver.findElements(By.xpath("//vms-tab[@label='Search Results']//div[contains(@class,'boxes')][p[position()=2][text()='"+vendorNm+"']]/p/input"));
        if(objCheckbox.size()>0) {
            objCheckbox.get(0).click();
            objSideBar.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Next']")).click();
            Thread.sleep(2000);
            objSideBar.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Next']")).click();
            Thread.sleep(2000);
            objSideBar.findElement(By.xpath("//button[@type='submit'][normalize-space(text())='Done']")).click();
            Thread.sleep(4000);
        }else
            System.out.println("No vendor with name as "+ vendorNm + " is displayed in search results");

    }

    public static void approveJob(String approverDesignation, String approverName) throws InterruptedException {
        actOnJob(approverDesignation, approverName, "Approve");
    }

    public static void rejectJob(String approverDesignation, String approverName) throws InterruptedException {
        actOnJob(approverDesignation, approverName, "Reject");
    }

    public static void approveJob(String approverName) throws InterruptedException {
        actOnJob(approverName, "Approve");
    }

    public static void rejectJob(String approverName) throws InterruptedException {
        actOnJob(approverName, "Reject");
    }

    private static void actOnJob(String actorDesignation, String actorName, String action) throws InterruptedException {
        driver.findElement(By.xpath("//div[div/p[contains(normalize-space(text()),'"+actorDesignation+"')]]/following-sibling::div//app-approval-member-profile[//div[1]/span[text()='"+actorName+"']]/div[2]/button[normalize-space(text())='"+action+"']")).click();
        Thread.sleep(4000);
        SidePage.accept(action);
    }

    private static void actOnJob(String actorName, String action) throws InterruptedException {
        driver.findElement(By.xpath("//div[div/p]/following-sibling::div//app-approval-member-profile[//div[1]/span[text()='"+actorName+"']]/div[2]/button[normalize-space(text())='"+action+"']")).click();
        Thread.sleep(4000);
        SidePage.accept(action);
    }

    public static String getOfferStatus(){
        return driver.findElement(By.xpath("//app-candidate-job-view-offers//span[contains(@class,'status')]")).getText().trim();
    }

    public static void approveOffer(String approverDesignation, String approverName) throws InterruptedException {
        actOnOffer(approverDesignation, approverName, "Approve");
    }

    public static void rejectOffer(String rejectorDesignation, String rejectorName, String rejectReason) throws InterruptedException {
        actOnOffer(rejectorDesignation, rejectorName, "Reject");
    }

    private static void actOnOffer(String actorDesignation, String actorName, String action) throws InterruptedException {
        driver.findElement(By.xpath("//div[div/p[contains(normalize-space(text()),'"+actorDesignation+"')]]/div/div[1][//span[text()='"+actorName+"']]/div[2]/button[normalize-space(text())='"+action+"']")).click();
        Thread.sleep(3000);
        SidePage.accept(action);
    }

    public static void approveOffer(String approverName) throws InterruptedException {
        actOnOffer(approverName, "Approve");
    }

    public static void rejectOffer(String rejectorName, String rejectReason) throws InterruptedException {
        actOnOffer(rejectorName, "Reject");
    }

    private static void actOnOffer(String actorName, String action) throws InterruptedException {
        driver.findElement(By.xpath("//div[div/p]/div/div[1][//span[text()='"+actorName+"']]/div[2]/button[normalize-space(text())='"+action+"']")).click();
        Thread.sleep(3000);
        SidePage.accept(action);
    }

    public static void viewApprovalNotification() throws InterruptedException {
        driver.findElement(By.xpath("//div[contains(@class,'approval-nofification')]")).click();
        Thread.sleep(3000);
    }

    private static void clickMenu(String rowToHover, String action) throws InterruptedException {
        Thread.sleep(2000);
        String dotAction = rowToHover + "//span[@class='actions']/span/table-icon[@name='more_horiz']";
        String strAction = "//nav[@id='rowDropdown' and contains(@class, 'active')]/ul/li[not(@hidden)]/a[normalize-space(text())='"+action+"']";
        String elemStr = rowToHover + "/vms-row[1]/td";
        System.out.println(elemStr);
        //WebElement hoverElem = driver.findElement(By.xpath(elemStr));
        Thread.sleep(4000);
        //hoverElement(hoverElem);
        hoverElement(elemStr);
        Thread.sleep(4000);
        WebElement dotActionMenu = driver.findElement(By.xpath(dotAction));
        //Assert.assertTrue("3 Dot Menu not displaying", isElementDisplayed(dotActionMenu));
        System.out.println("3 Dot Menu is displayed on Interviews Page");
        dotActionMenu.click();
        Thread.sleep(3000);
        WebElement actionLink = driver.findElement(By.xpath(strAction));
        actionLink.click();
        Thread.sleep(5000);
    }
    public static void clickActionMenu(String action) throws InterruptedException {
        clickActionMenu(action, "");
    }

    public static void clickActionMenu(String action, String status) throws InterruptedException {
        String elemClass = status.toLowerCase();
        String rowToHover = null;
        if(status != "")
            rowToHover = "(//table[contains(@class,'cloned')]/tbody/tr[vms-row[2]/td/span[contains(.,'"+elemClass+"')]])[1]";
        else
            rowToHover = "//table[contains(@class,'cloned')]/tbody/tr[1]";
        clickMenu(rowToHover, action);
        Thread.sleep(10000);
    }

    /*
    public static void clickActionMenu(String action, String filterByCol, String filterColValue) throws InterruptedException {
        String rowToHover = null;
        rowToHover = "//table[contains(@class,'cloned')]/tbody/tr[1]";
        clickMenu(rowToHover, action);
        Thread.sleep(5000);
    }
     */

    public static void hoverElement(WebElement element) throws InterruptedException {
        WebElement row = driver.findElement(By.xpath("//table[1]/tbody/tr[1]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",row);
        //scrollToElement(row);
        Thread.sleep(4000);
        //WebElement elem = driver.findElement(By.xpath("//table[1]/tbody/tr[1]/vms-row[1]/td"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void hoverElement(String elementStr) throws InterruptedException {
        WebElement row = driver.findElement(By.xpath(elementStr));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",row);
        //scrollToElement(row);
        Thread.sleep(4000);
        //WebElement elem = driver.findElement(By.xpath("//table[1]/tbody/tr[1]/vms-row[1]/td"));
        Actions action = new Actions(driver);
        action.moveToElement(row).build().perform();
    }

    public static void shortlist_candidate() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Shortlist']")).click();
        Thread.sleep(4000);
    }

    public static void acceptInterview() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Accept']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//input[@formcontrolname='phone']")).sendKeys("7874387983");
        SidePage.accept("Accept Interview");
        Thread.sleep(8000);
    }
}
