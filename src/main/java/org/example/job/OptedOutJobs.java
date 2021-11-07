package org.example.job;

import org.example.Sidebar;

public class OptedOutJobs extends Sidebar implements FilterTable {

    public static void navigateToJob() throws InterruptedException {
        FilterTable.navigateToFirstItemInList();
        Thread.sleep(4000);
    }

    public static void navigateToJob(String jobTitle){

    }

    public static void navigateToJob(String jobTitle, String status){

    }
}
