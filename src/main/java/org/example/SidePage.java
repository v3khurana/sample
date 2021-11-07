package org.example;

import org.openqa.selenium.By;

public class SidePage extends BasePage{

    public static void clickXbuttonToClose() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='close-trigger']")).click();
        if(driver.findElement(By.xpath("//div[@id='svmsSidebar']")).getAttribute("hidden").equals("hidden"))
            System.out.println("Side page closed");
    }
}
