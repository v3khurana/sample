package org.example.candidate;

import org.example.BasePage;
import org.example.SidePage;
import org.openqa.selenium.By;

import java.io.File;

public class NewCandidate extends BasePage {

    public static void upload_avatar_image() throws InterruptedException {
        Thread.sleep(1000);
        //driver.findElement(By.xpath("//div[contains(@class,'upload-avatar-panel')]//input")).sendKeys("C:\\Users\\VarunKhurana\\qa\\trial1\\resources\\pic.jpeg");
        File file = new File("resources/pic.jpeg");
        driver.findElement(By.xpath("//div[contains(@class,'upload-avatar-panel')]//input")).sendKeys(file.getAbsolutePath());
        Thread.sleep(6000);
        driver.findElement(By.xpath("//div[contains(@class,'cropping-avtar-image')]//button[normalize-space(text())='Crop & Save']")).click();
        Thread.sleep(3000);
    }

    public static void upload_resume() throws InterruptedException {
        //driver.findElement(By.xpath("//div[@class='resume-uploader']//input")).sendKeys("C:\\Users\\VarunKhurana\\qa\\trial1\\resources\\resume.doc");
        File file = new File("resources/resume.doc");
        driver.findElement(By.xpath("//div[@class='resume-uploader']//input")).sendKeys(file.getAbsolutePath());
        Thread.sleep(6000);
        driver.findElement(By.xpath("//div[contains(@class,'modal-footer')]/button[text()='No']")).click();
        Thread.sleep(3000);
    }

    private static void enterBasicInfo(String firstName, String lastName, String dob, String country, String nationalID) throws InterruptedException {
        driver.findElement(By.xpath("//input[@formcontrolname='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@formcontrolname='lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//div[label[normalize-space(text())='Date of Birth']]//input")).click();
        Thread.sleep(3000);
        selectDateInMMDD(dob);
        Thread.sleep(2000);
        selectDropdownData("Country", country);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@formcontrolname='stateid']")).sendKeys(nationalID);
    }

    private static void enterContactInfo(String emailAddr, String primaryAddr){
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(emailAddr);
        driver.findElement(By.xpath("//input[@id='addressField']")).sendKeys(primaryAddr);
    }

    private static void enterEducation(String education) throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[contains(@class,'btn-add-item')]")).click();
        SidePage.selectDropdownData("Select Education", education);
        SidePage.accept(driver.findElement(By.xpath("//app-add-education")),"Save");
        Thread.sleep(3000);
    }

    private static void enterCertifications(String certification, String certificationAuthority, String certificationDt) throws InterruptedException {
        driver.findElement(By.xpath("//button[contains(@class,'btn-add-item')]")).click();
        SidePage.selectDropdownData("Select Certification", certification);
        driver.findElement(By.xpath("//input[@formcontrolname='authority']")).sendKeys(certificationAuthority);
        driver.findElement(By.xpath("//svms-datepicker[@formcontrolname='initialDate']//input")).click();
        selectDate(certificationDt);
        SidePage.accept(driver.findElement(By.xpath("//app-add-certification")),"Save");
        Thread.sleep(3000);
    }

    private static void enterCredentials(String credential, String issuingAuthority, String initialIssuingDt) throws InterruptedException {
        driver.findElement(By.xpath("//button[contains(@class,'btn-add-item')]")).click();
        SidePage.selectDropdownData("Select Credential", credential);
        driver.findElement(By.xpath("//input[@formcontrolname='issuingAuthority']")).sendKeys(issuingAuthority);
        driver.findElement(By.xpath("//svms-datepicker[@formcontrolname='initialIssuingDate']//input")).click();
        selectDate(initialIssuingDt);
        SidePage.accept(driver.findElement(By.xpath("//app-add-credentials")),"Save");
        Thread.sleep(3000);
    }

    /*
    private static void enterVaccination(String vaccination, String vaccinationProvider, String vaccinationDt, String vaccinationExpDt, String vaccinationNUm) throws InterruptedException {
        driver.findElement(By.xpath("//button[contains(@class,'btn-add-item')]")).click();
        SidePage.selectDropdownData("Select Credential", credential);
        driver.findElement(By.xpath("//input[@formcontrolname='issuingAuthority']")).sendKeys(issuingAuthority);
        driver.findElement(By.xpath("//svms-datepicker[@formcontrolname='initialIssuingDate']//input")).click();
        selectDate(initialIssuingDt);
        SidePage.accept(driver.findElement(By.xpath("//app-add-credentials")),"Save");
        Thread.sleep(3000);
    }


     */
    public static void enterCandidateInfo(String firstName, String lastName, String dob, String country, String nationalID,String emailAddr, String primaryAddr, String education) throws InterruptedException {
        upload_avatar_image();
        upload_resume();
        enterBasicInfo(firstName, lastName, dob, country, nationalID);
        enterContactInfo(emailAddr, primaryAddr);
        enterEducation(education);
        driver.findElement(By.xpath("//button[contains(@class,'create-candidate-btn')]")).click();
        //driver.findElement(By.xpath("//svms-sidebar//button[normalize-space(text())='Create Candidate']")).click();
        Thread.sleep(6000);
    }

}
