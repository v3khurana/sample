package org.example;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Sidebar extends BasePage{

    public static void navigate(String pageIconLabel, String subMenuLabel) throws InterruptedException {
        Thread.sleep(10000);
        // Click Breadcrumb
        /*
        driver.findElement(By.xpath("//a[@class='switch-menu']")).click();
        Thread.sleep(5000);
        logger.log(Status.PASS, "Side Menu opened");
        driver.findElement(By.xpath("//a[contains(@class,'nav-item')][label[text()='" + pageIconLabel + "']]")).click();
        logger.log(Status.PASS, "Side Menu - " + pageIconLabel + " Category clicked");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//ul[contains(@class,'submenu')]/li/a[normalize-space(text())='" + subMenuLabel + "']")).click();
        Thread.sleep(8000);
*/
        BasePage bp = new BasePage();
        bp.click("Side Menu Breadcrumb",By.xpath("//a[@class='switch-menu']"),8);
        logger.log(Status.PASS, "Side Menu opened");
        bp.click(By.xpath("//a[contains(@class,'nav-item')][label[text()='" + pageIconLabel + "']]"),5);
        logger.log(Status.PASS, "Side Menu - " + pageIconLabel + " Category clicked");
        bp.click(By.xpath("//ul[contains(@class,'submenu')]/li/a[normalize-space(text())='" + subMenuLabel + "']"),8);
        getEvidence(Status.PASS, "Side Menu - " + subMenuLabel + " Category clicked");
    }
}
/*
    public static void uploadResume(String absoluteFileName, String autoFillCandidateDetails){
        WebElement objUploadResume = driver.findElement(By.xpath("//div[contains(@class,'browse-zone')]/input"));
        objUploadResume.sendKeys(absoluteFileName);
        Thread.sleep(6000);
        driver.findElement(By.xpath("//app-confirmation-dialog/div/div[2]/button[text()='"+autoFillCandidateDetails+"']")).click();
        Thread.sleep(10000);
    }
*/

