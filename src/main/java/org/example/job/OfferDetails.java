package org.example.job;

import org.example.SidePage;
import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class OfferDetails extends Sidebar {

    public static void navigateTabs(String tabName) throws InterruptedException {
        try {
            driver.findElement(By.xpath("//div[@class='nav-wrapper']//li/a[normalize-space(text())='"+tabName+"']")).click();
        }catch(Exception noTab){
            System.out.println("Tab named "+ tabName + " does not exists");
        }
        Thread.sleep(8000);
    }

    public static String getOfferStatus(){
        return driver.findElement(By.xpath("//div[@class='job-view-content-header']/span[contains(@class,'status')]")).getText().trim();
    }

    public static String getOfferId(){
        return driver.findElement(By.xpath("//div[@class='sub-head-info']/label[1]/span")).getText().trim();
    }


}
