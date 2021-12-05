package org.example.job;

import org.example.Sidebar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class BackgroundCheck extends Sidebar {

    public static void completeCheck() throws InterruptedException {

        List<WebElement> rows = driver.findElements(By.xpath("//app-credentialing-background-check//div[contains(@class,'check-grid')]"));
        for(WebElement row : rows){
            // Mark as Completed
            row.findElement(By.xpath("//input")).click();
            Thread.sleep(1000);
            row.findElement(By.xpath("//label[@class='radio-container']/input[@value='PASSED']")).click();
            Thread.sleep(10000);
            assertTrue(row.findElement(By.xpath("//div[@class='status-ui completed']")).isDisplayed(),"Background Check not marked as Completed");
        }
    }

}
