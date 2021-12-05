package org.example;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BasePage {

    public static WebDriver driver = null;
    public static ExtentTest logger = null;

    public WebDriver initializeDriver(){
        DriverProvider dp = new DriverProvider();
        driver = dp.getDriver();
        return driver;
    }

    /*
    public ExtentTest setLogger(Method m){
        return logger;
    }

     */

    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "\\FailedTestsScreenshots\\"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public void click(By byObj){
        click(byObj, 0);
    }

    public void click(By byObj, int waitInSec){
        int waitTime = 0;
        if(waitInSec == 0)
            waitTime = 1500;
        else
            waitTime = waitInSec*1000;
        try {
        driver.findElement(byObj).click();
        Thread.sleep(waitTime);
        }catch(InterruptedException e){}
        catch(NoSuchElementException ex){
            logger.log(Status.FAIL,"Element not found");
        }
    }

    public void click(String linkName, By byObj, int waitInSec) {
        int waitTime = 0;
        if (waitInSec == 0)
            waitTime = 1500;
        else
            waitTime = waitInSec * 1000;
        try {
            driver.findElement(byObj).click();
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
        } catch (NoSuchElementException ex) {
            try {
                String screenshotPath = BasePage.getScreenshot(driver, "Sidebar");
                logger.log(Status.FAIL, "Unable to click as field : " + linkName + " not found", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }catch(Exception e){}
        }
    }

    public static void getEvidence(Status status, String msg){
        try {
            String screenshotPath = BasePage.getScreenshot(driver, "Sidebar");
            logger.log(status, msg, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }catch(Exception e){}
    }

    public void enter(By byObj, String data){
        try {
            driver.findElement(byObj).sendKeys(data);
            Thread.sleep(1500);
        }catch(InterruptedException e){}
        catch(NoSuchElementException ex){
            logger.log(Status.FAIL,"Element not found");
        }
    }

    public void enter(String fieldName, By byObj, String data){
        try {
            driver.findElement(byObj).sendKeys(data);
            Thread.sleep(1500);
        }catch(InterruptedException e){}
        catch(NoSuchElementException ex){
            logger.log(Status.FAIL,"Unable to enter data in as field : "+fieldName+" not found");
        }
    }

    public static void selectDate(String dt) throws InterruptedException {
        if(dt.equals("Today's date")) {
            try{
                driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day -current')]")).click();
            }catch(Exception e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                System.out.println(formatter.format(LocalDate.now()));
                selectDate(formatter.format(LocalDate.now()));
            }
        }else {
            String year = dt.split("/")[2].toString();
            int month = Integer.parseInt(dt.split("/")[1]);
            String day = String.valueOf(Integer.parseInt(dt.split("/")[0]));
            System.out.println("String date is " + dt + " year" + " " + year + " " + " month" + " " + month + " " + " day" + " " + day);
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
            Thread.sleep(3000);
            //driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")).click();
            js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
            Thread.sleep(1000);
            //driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")).click();
            js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
            Thread.sleep(1000);
            //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='" + year + "']")).click();
            js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='" + year + "']")));
            Thread.sleep(1000);
            String temp = Month.of(month).name().toLowerCase();
            String monthName = temp.substring(0, 1).toUpperCase() + temp.substring(1);
            //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and translate(normalize-space(text()) ,'abcdefghijklmnopqrstuvwxyz' ,'ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+ Month.of(month).name()+"']")).click();
            //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='" + monthName + "']")).click();
            js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='" + monthName + "']")));
            Thread.sleep(1000);
            //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month'))][span[text()='" + day + "']]")).click();
            js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month'))][span[text()='" + day + "']]")));
        }
    }

    public static void selectDateInMMDD(String dt) throws InterruptedException {

        String year = dt.split("/")[2].toString();
        int month = Integer.parseInt(dt.split("/")[1]);
        String day = String.valueOf(Integer.parseInt(dt.split("/")[0]));
        System.out.println("String date is "+dt+" year"+ " "+year + " "+" month" + " "+ month + " "+ " day"+ " "+ day);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
        Thread.sleep(1000);
        //driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
        Thread.sleep(1000);
        //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='"+year+"']")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='"+year+"']")));
        Thread.sleep(1000);
        String temp = Month.of(month).name().toLowerCase();
        String monthName = temp.substring(0, 1).toUpperCase() + temp.substring(1);
        //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and translate(normalize-space(text()) ,'abcdefghijklmnopqrstuvwxyz' ,'ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+ Month.of(month).name()+"']")).click();
        //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='"+ monthName+"']")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='"+ monthName+"']")));
        Thread.sleep(1000);
        //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month'))][span[text()='"+day+"']]")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month'))][span[text()='"+day+"']]")));
    }

    public static void selectDateRange(String startdt, String endDt) throws InterruptedException {
        selectDtInRange(startdt, "//div[contains(@class,'datepicker left-rangepicker-right')]");
        selectDtInRange(endDt, "//div[contains(@class,'datepicker right-rangepicker-left')]");
    }

    private static void selectDtInRange(String dt, String container) throws InterruptedException {
        String year = dt.split("/")[2].toString();
        int month = Integer.parseInt(dt.split("/")[1]);
        String day = dt.split("/")[0].toString();
        System.out.println("String date is "+dt+" year"+ " "+year + " "+" month" + " "+ month + " "+ " day"+ " "+ day);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
        Thread.sleep(2000);
        //driver.findElement(By.xpath(container+"//nav/div[2]/i")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]/i")));
        Thread.sleep(1000);
        //driver.findElement(By.xpath(container+"//nav/div[2]")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//div[@class='datepicker']//nav/div[2]")));
        Thread.sleep(1000);
        //driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='"+year+"']")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='"+year+"']")));
        Thread.sleep(1000);
        String temp = Month.of(month).name().toLowerCase();
        String monthName = temp.substring(0, 1).toUpperCase() + temp.substring(1);
        //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and translate(normalize-space(text()) ,'abcdefghijklmnopqrstuvwxyz' ,'ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+ Month.of(month).name()+"']")).click();
        //driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='"+ monthName+"']")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='"+ monthName+"']")));
        Thread.sleep(1000);
        //driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month'))][span[text()='"+day+"']]")).click();
        //driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month')) and text()='"+day+"']")).click();
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month')) and text()='"+day+"']")));
    }

    public static void selectDropdownData(String label, String data) throws InterruptedException {
        //WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[div/label[normalize-space(text())='"+label+"']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        //WebElement dropdown = driver.findElement(By.xpath("//ng-select[preceding-sibling::label[normalize-space(text())='"+label+"']]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        WebElement dropdown = driver.findElement(By.xpath("//ng-select[preceding-sibling::label[normalize-space(text())='"+label+"']]/div/div/div[@class='ng-placeholder']/following-sibling::div/input|//label[normalize-space(text())='"+label+"']/following-sibling::div/ng-select/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
        Thread.sleep(2000);
        dropdown.click();
        Thread.sleep(2000);
        dropdown.sendKeys(data);
        Thread.sleep(12000);
        driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[normalize-space(text())='" + data + "']]")).click();
        Thread.sleep(2000);
    }

    public static void selectRandomDropdownData(String label, String data) throws InterruptedException {
        //WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[div/label[normalize-space(text())='"+label+"']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        //WebElement dropdown = driver.findElement(By.xpath("//ng-select[preceding-sibling::label[normalize-space(text())='"+label+"']]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        WebElement dropdown = driver.findElement(By.xpath("//ng-select[preceding-sibling::label[normalize-space(text())='"+label+"']]/div/div/div[@class='ng-placeholder']/following-sibling::div/input|//label[normalize-space(text())='"+label+"']/following-sibling::div/ng-select/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
        Thread.sleep(2000);
        dropdown.click();
        Thread.sleep(3500);
        List<WebElement> dropdrownList = driver.findElements(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)]"));
        dropdrownList.get(getRandomNumber(0,dropdrownList.size()-1));
        Thread.sleep(2000);
    }

    public static void enterTextInCustomField(String label, String data){
        driver.findElement(By.xpath("//div[label[normalize-space(text())='"+label+"']]/input[@formcontrolname='values']")).sendKeys(data);
    }

    private static int getRandomNumber(int ll, int hl){
        Random random = new Random();
        random.ints(1, ll, hl);
        return 0;
    }
}
