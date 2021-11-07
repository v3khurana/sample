package org.example;

//import static org.junit.Assert.assertTrue;

//import org.junit.Test;

import org.example.job.FilterTable;
import org.example.job.JobDetails;
import org.example.job.JobList;
import org.example.job.OptedOutJobs;
import org.example.programs.ProgramCreation;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        assertTrue( true );
        /*
        Login lg = new Login();
        lg.login("https://qa2-app.simplifysandbox.net/auth/login","simplifyqa+tomfelton@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Programs", "Create Program");
        ProgramCreation.createProgram();*/
    }

    @Test
    public void shouldAnswerWithTrue1() throws Exception {
        assertTrue( true );
        /*
        Login lg = new Login();
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job", "View All Job");
        JobList.filter("Sourcing");
        Thread.sleep(8000);
        System.out.println("Filter applied");
        //JobList obj = new JobList();
        FilterTable.removeActiveFilters();
        System.out.println("Filter removed");
        */
    }

    @Test
    public void shouldAnswerWithTrue2() throws Exception {
        assertTrue( true );
        Login lg = new Login();
        //lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");
        lg.login("https://qa2-app.simplifysandbox.net/auth/login","dillikumar+msptfsb@simplifyvms.com","Yahoo@2021");
        Sidebar.navigate("Job", "View All Job");
        //OptedOutJobs.navigateToJob();
        //JobDetails.navigateTabs("Available Candidates");
        FilterTable.navigateToFirstItemInList();
    }
}
