package org.example;

//import static org.junit.Assert.assertTrue;

//import org.junit.Test;

import org.example.job.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest_old
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
        /*
        Login lg = new Login();
        //lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");
        lg.login("https://qa2-app.simplifysandbox.net/auth/login","dillikumar+msptfsb@simplifyvms.com","Yahoo@2021");
        Sidebar.navigate("Job", "View All Job");
        //OptedOutJobs.navigateToJob();
        //JobDetails.navigateTabs("Available Candidates");
        FilterTable.navigateToFirstItemInList();
        */

    }

    @Test
    public void createJob() throws Exception {

    }

    @Test(dependsOnMethods = "createJob")
    public void approveJob_JobMnaager() throws Exception {
    }

    public void approve(){

    }

    @Test
    public void job8() throws Exception {
        Login lg = new Login();

        //String jobID = "AMFAM MSP-JB-578";
        //String jobManager = "Connor Hm";
        //String hiringManager = "Andy Hm+1";

        //Login with MSP to create/clone a job
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job", "Create Job");

        //Create New Job
        NewJob.createJob();
        String jobID = JobDetails.getJobId();
        System.out.println("Created job is "+ jobID);

        //Verify created job is in "Pending Approval" state
        assertEquals(JobDetails.getJobStatus(),"Pending Approval");

        //Get Details of Job Manager and Hiring Manager
        JobDetails.navigateTabs("Approval");
        String jobManager = JobDetails.getJobManager();
        System.out.println("Job Manager is " + jobManager);
        String hiringManager = JobDetails.getHiringManager();
        System.out.println("Hiring Manager is "+ hiringManager);

        //Logout from MSP login
        lg.logout();

        // Login with Job Manager to approve the above created job
        lg.login("https://preprod-app.simplifyvms.com/","avik.mistry+connor@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Approval");
        JobDetails.approveJob("Job Manager", jobManager);

        //Logout from Job Manager login
        lg.logout();

        // Login with Job Manager+1 to approve the above created job
        lg.login("https://preprod-app.simplifyvms.com/","avik.mistry+andy@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Approval");
        JobDetails.approveJob("Hiring Manager", hiringManager);

        //Verify created job is in "OPEN" state
        assertEquals(JobDetails.getJobStatus(),"OPEN");

        //Logout from JobManager+1 login
        lg.logout();

        // Login with MSP to distribute above created job to Vendors
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Distribution");
        JobDetails.distributeJob();

        //Verify created job is in "Sourcing" state
        assertEquals(JobDetails.getJobStatus(),"Sourcing");

        //Logout from MSP login
        lg.logout();

        // Login with Vendor to submit candidate to the above created job
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Available Candidates");

        //TODO
        // Submit Candidate
        JobDetails.clickActionMenu("Submit Candidate");
        CandidateSubmission.enterCandidateSubmissionDetails();
        FilterTable.navigateToFirstItemInList();
        // Verify status of candidate is in Pending shortlist state
        //assertEquals(FilterTable.getColValueAgainst("", "Status"),"pending shortlist");

        //Logout from Vendor login
        lg.logout();

        // Login with MSP to shortlist candidate
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Submitted Candidates");
        //TODO
        // Navigate to candidate and shortlist
        FilterTable.navigateToFirstItemInList();
        JobDetails.shortlist_candidate();
        // Verify status of candidate is in Submitted state
        //assertEquals(FilterTable.getColValueAgainst("", "Status"),"submitted");

        //Logout from MSP login
        lg.logout();

        // Login with Hiring Manager to schedule interview for candidate
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Submitted Candidates");
        JobDetails.clickActionMenu("Create Offer");
        NewOffer.clickCreateOfferBtn();
        NewOffer.createNewOffer();
        //Verify created offer is in "Pending Approval" state
        assertEquals(JobDetails.getOfferStatus(),"Pending Approval");

        //Logout from MSP login
        lg.logout();

        // Login with Offer Manager to approve offer for candidate
        lg.login("https://preprod-app.simplifyvms.com/","avik.mistry+connor@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Offers");
        FilterTable.navigateToFirstItemInList();
        JobDetails.viewApprovalNotification();
        JobDetails.approveOffer("Offer Manager", jobManager);

        //Logout from Offer Manager login
        lg.logout();

        // Login with offer Manager+1 to approve the above created offer
        lg.login("https://preprod-app.simplifyvms.com/","avik.mistry+andy@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Offers");
        FilterTable.navigateToFirstItemInList();
        JobDetails.viewApprovalNotification();
        JobDetails.approveOffer("Hiring Manager", hiringManager);

        //Verify created job is in "Released" state
        assertEquals(JobDetails.getOfferStatus(),"Released");

        //Logout from Job Manager+1 login
        lg.logout();

        // Login with Vendor to accept the above created offer
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Offers");
        //FilterTable.navigateFromActionMenu("Accept Offer");
        JobDetails.clickActionMenu("Accept Offer");
        SidePage.accept("Accept Offer");
        //TODO
        // Verify status of Candidate offer is 'Accepted'
        FilterTable.navigateToFirstItemInList();
        OfferDetails.navigateTabs("Background Check");
        BackgroundCheck.completeCheck();

        //TODO
        // Complete Setup

        //TODO
        // Enter Timesheet








    }
}
