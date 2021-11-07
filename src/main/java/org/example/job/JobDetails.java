package org.example.job;

import org.example.Sidebar;
import org.openqa.selenium.By;

import java.util.HashMap;

public class JobDetails extends Sidebar {

    public static void navigateTabs(String tabName) throws InterruptedException {
        try {
            driver.findElement(By.xpath("//ul[@class='tabs primary']/li/a[normalize-space(text())='" + tabName + "']")).click();
        }catch(Exception noTab){
            driver.findElement(By.xpath("//ul[@class='tabs primary']/li/a[normalize-space(text())='More']")).click();
            Thread.sleep(2000);
            try{
                driver.findElement(By.xpath("//ul[@class='tabs primary']/li/a[normalize-space(text())='" + tabName + "']")).click();
            }catch (Exception noTabInMore){
                System.out.println("Tab named "+ tabName + " does not exists");
            }
        }
        Thread.sleep(6000);
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


}
