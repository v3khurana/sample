package org.example;

import org.openqa.selenium.By;

public class Dashboard extends BasePage{

    public void navigate(String url, String email, String password){
        driver.get(url);
        // Wait until email field is displayed
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        //Wait until sign in button gets enabled
        driver.findElement(By.xpath("//button[@type='submit' and not(@disabled)]")).click();
        //Wait until aap-header gets displayed
    }
}
