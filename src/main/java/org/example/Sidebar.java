package org.example;

import org.openqa.selenium.By;

public class Sidebar extends BasePage{

    public static void navigate(String pageIconLabel, String subMenuLabel) throws InterruptedException {
        Thread.sleep(5000);
        // Click Breadcrumb
        driver.findElement(By.xpath("//a[@class='switch-menu']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[contains(@class,'nav-item')][label[text()='"+ pageIconLabel +"']]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//ul[contains(@class,'submenu')]/li/a[normalize-space(text())='"+subMenuLabel+"']")).click();
        Thread.sleep(8000);
    }
}
