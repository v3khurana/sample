package org.example;

import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class Login extends BasePage{

    By objEmail = By.xpath("//input[@name='email']");
    By objPwd = By.xpath("//input[@name='password']");
    By objSubmit = By.xpath("//button[@type='submit' and not(@disabled)]");
    By ele_sidebar = By.xpath("//*[@class='sidebar-inner mb-64 position-fixed']");

    public Login(){
        super.initializeDriver();
    }

    public void login(String url, String email, String password) throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(url);
        logger.log(Status.PASS,"Navigated to Pre-Prod URL");
        enter(objEmail, email);
        enter(objPwd, password);
        logger.log(Status.PASS,"Credentials entered on login page");
        click(objSubmit,40);
        //assertTrue(driver.findElement(ele_sidebar).isDisplayed(),"Not in Login page" );
        //Dashboard Page object can be returned
    }

    public void login(String env, String role) throws InterruptedException {
        // Get url, email, password through env, role
        String url = "";
        String email = "";
        String password = "";
        login(url, email, password);
    }

    public void logout() throws InterruptedException {
        /*
        driver.findElement(By.xpath("//div[@class='user-settings']//figure")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[normalize-space(text())='Logout']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'modal-footer')]/button[text()='Yes']")).click();
        Thread.sleep(3000);
        */
        click(By.xpath("//div[@class='user-settings']//figure"),2);
        click(By.xpath("//a[normalize-space(text())='Logout']"),5);
        click(By.xpath("//div[contains(@class,'modal-footer')]/button[text()='Yes']"),10);
        logger.log(Status.PASS,"Logged out of the application");

    }

    public void closeBrowser(){
        driver.quit();
    }

    public void forgotCreds(String usernameOrPwd, String email){
        driver.findElement(By.xpath("//a[contains(@class,'forgot-links')][normalize-space(text())='"+usernameOrPwd+"']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
