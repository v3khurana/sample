package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SidePage extends BasePage{

    public static void clickXbuttonToClose() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='close-trigger']")).click();
        if(driver.findElement(By.xpath("//div[@id='svmsSidebar']")).getAttribute("hidden").equals("hidden"))
            System.out.println("Side page closed");
    }

    public static void accept(String btnName) throws InterruptedException {
        driver.findElement(By.xpath("//div[@id='svmsSidebar']//button[normalize-space(text())='"+btnName+"']")).click();
        Thread.sleep(5000);
    }

    public static void accept(WebElement wrapperElem, String btnName) throws InterruptedException {
        //wrapperElem.findElement(By.xpath(".//div[@id='svmsSidebar']/svms-sidebar-footer-ng/div/button[normalize-space(text())='"+btnName+"']")).click();
        wrapperElem.findElement(By.xpath(".//div[@id='svmsSidebar']//button[normalize-space(text())='"+btnName+"']")).click();
        Thread.sleep(5000);

    }
}
