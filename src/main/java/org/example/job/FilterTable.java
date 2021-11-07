package org.example.job;

import org.openqa.selenium.By;

import java.util.HashMap;

import static org.example.BasePage.driver;

public interface FilterTable {

    static void removeActiveFilters() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='clear-filter']")).click();
        Thread.sleep(2000);
    }

    static void navigateToFirstItemInList(){
        driver.findElement(By.xpath("(//table[contains(@class,'cloned')]/tbody/tr[vms-row[2]/td/span])[1]/vms-row[1]//p/a")).click();
    }

    static void navigateToFirstItemInList(HashMap<String, String> filtersData){
        //TODO
        //driver.findElement(By.xpath("(//table[contains(@class,'cloned')]/tbody/tr[vms-row[2]/td/span])[1]/vms-row[1]//p/a")).click();
    }

    static void navigateFromActionMenu(String action){

    }


}
