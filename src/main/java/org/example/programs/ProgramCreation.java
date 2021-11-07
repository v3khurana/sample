package org.example.programs;

import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProgramCreation extends Sidebar {

    public static void createProgram() throws Exception {
        enterProgramDetails();
        //selectProgramConfiguration();
        selectProgramMembers("Integration Lead", "andrew  strass");
        selectModules("Assignment", "Invoice", "Direct Sourcing");
        selectProgramConfiguration();
        Thread.sleep(5000);
    }

    public void go_back(){
        driver.findElement(By.xpath("//a[@class='back-button icon-only']")).click();
        // TODO
        // Verify if navigated to all Programs list page
    }

    private static void enterProgramDetails() throws Exception {
        String clientName = "test123";
        String programType = "MSP-MANAGED";
        String newProgName = "abc11sdf";
        String newIndustryName = "asdf";

        selectPerLabel("Client", clientName);
        selectPerLabel("Program Type", programType);
        if(programType.equals("MSP-MANAGED"))
            selectPerLabel("MSP", "JPMorgan MSP");

        // Enter Program Name
        driver.findElement(By.xpath("//input[@placeholder='Enter Your Program Name']")).sendKeys(newProgName);

        // Enter Program Industry
        driver.findElement(By.xpath("//input[@id='industries']")).sendKeys(newIndustryName + Keys.ENTER);
    }

    private static void selectProgramConfiguration() throws Exception {
        selectPerLabel("Select Date Format", "DD/MM/YYYY");
    }

    private static void selectPerLabel(String label, String option) throws Exception{
        try {
            //driver.findElement(By.xpath("(//ng-select[ancestor::div[*/label|label[normalize-space(text())='" + label + "']]]/div/div/div[@class='ng-placeholder']/following-sibling::div/input)[1]")).click();
            WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[label[normalize-space(text())='"+label+"']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
            //WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[label[normalize-space(text())='"+label+"']]])[1]/div[div/div[@class='ng-placeholder']/following-sibling::div/input]/span[contains(@class,'ng-arrow')]"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
            Thread.sleep(2000);
            dropdown.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[text()='" + option + "']]")).click();
            Thread.sleep(2000);
        }catch(InterruptedException ie){
        }
    }

    private static void selectProgramMembers(String position, String name_or_email) throws InterruptedException {
        try {
            //selectPerLabel("Choose Position", position);
            WebElement dropdown = driver.findElement(By.xpath("(//ng-select[ancestor::div[app-program-manage/label[normalize-space(text())='Select User and Position']]])[1]/div/div/div[@class='ng-placeholder']/following-sibling::div/input"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView({block: \"center\"});",dropdown);
            Thread.sleep(2000);
            dropdown.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[contains(@class,'ng-option')][(span/span|span)[text()='" + position + "']]")).click();
        } catch (Exception e) {
            System.out.println("Unable to select Position or no position named " + position + " exists");
        }
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@placeholder='Add user by their name or email address'][1]")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //String user_name = (String) js.executeScript("return document.querySelector(\"div[class^='dropdown-user'] > div\").childNodes[1].textContent");
        List<WebElement> userNodes = (List<WebElement>) js.executeScript("return document.querySelectorAll(\"div[class^='dropdown-user'] > div\")");
        String user_name = null;
        for(WebElement elem : userNodes) {
            user_name = (String) js.executeScript("return arguments[0].childNodes[1].textContent", elem);
            if (user_name.equals(name_or_email)){
                elem.click();
                Thread.sleep(1500);
                break;
            }else
                System.out.println("No user named " + name_or_email + " exists in User list");
        }
        //TODO
        //Verify if user selected
    }

    private static void selectModules(String... modulesList){
        if(modulesList.length > 0){
            for(String module : modulesList) {
                try {
                    driver.findElement(By.xpath("//app-modules//span[span[normalize-space(text())='" + module + "']]/following-sibling::span")).click();
                }catch (Exception e){
                    System.out.println("No module named " + module + " exists in list of app modules");
                }
            }
        }
    }
}
