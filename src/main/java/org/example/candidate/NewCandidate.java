package org.example.candidate;

import org.example.BasePage;
import org.openqa.selenium.By;

public class NewCandidate extends BasePage {

    public static void enterCandidateInfo(String firstName, String lastName, String emailAddr){
        driver.findElement(By.xpath("//input[@formcontrolname='first_name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@formcontrolname='last_name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(emailAddr);
        driver.findElement(By.xpath("//svms-sidebar//button[normalize-space(text())='Create Candidate']")).click();
    }

}
