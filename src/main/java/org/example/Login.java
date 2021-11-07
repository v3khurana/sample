package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login extends BasePage{

    By objEmail = By.xpath("//input[@name='email']");
    By objPwd = By.xpath("//input[@name='password']");
    By objSubmit = By.xpath("//button[@type='submit' and not(@disabled)]");

    public Login(){
        super.initializeDriver();
    }

    public void login(String url, String email, String password) throws InterruptedException {
        driver.get(url);
        enter(objEmail, email);
        enter(objPwd, password);
        click(objSubmit,8);
    }

    public void login(String env, String role) throws InterruptedException {
        // Get url, email, password through env, role
        String url = "";
        String email = "";
        String password = "";
        login(url, email, password);
    }
}
