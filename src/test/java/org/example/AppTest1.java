package org.example;

import org.example.job.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest1
{
    Login lg = new Login();
    String jobID = "";
    String[] approvers = {"Kirthika Muthukumaran", "John Paquette"};

    @Test(description = "MSP to create/clone a job", priority = 1)
    public void createNewJob1() throws InterruptedException {
        System.out.println("-------------------I am here in AppTest--------------------");
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job", "Create Job");

        //Create New Job
        NewJob.createJob();

        //Verify created job is in "Pending Approval" state
        assertEquals(JobDetails.getJobStatus(),"Pending Approval");

        String jobID = JobDetails.getJobId();
        //Get Details of Job Manager and Hiring Manager
        JobDetails.navigateTabs("Approval");
        String jobManager = JobDetails.getJobManager();
        System.out.println("Job Manager is " + jobManager);
        String hiringManager = JobDetails.getHiringManager();
        System.out.println("Hiring Manager is "+ hiringManager);
        System.out.println("Created job is "+ jobID);
        this.jobID = jobID;
        //return jobID;
    }

    @Test(dependsOnMethods = "createNewJob1", priority = 2)
    public void jobApproval() throws InterruptedException {
        //public void jobApproval(String jobID, String...approvers) throws InterruptedException {
        int approverCounter = 0;
        for(String approver : approvers){
            approverCounter++;
            // Login with Approver to approve the above created job
            lg.login("https://preprod-app.simplifyvms.com/", getEmail(approver), getPassword(approver));
            navigateTabsForJob(jobID, "Approval");
            JobDetails.approveJob(approver);

            //Logout from Job Manager login
            if(approverCounter < approvers.length)
                lg.logout();
        }

        //Verify created job is in "OPEN" state
        assertEquals(JobDetails.getJobStatus(),"OPEN");
    }

    @Test(dependsOnMethods = "jobApproval", description = "MSP to distribute above created job to Vendors", priority = 3)
    public void distributeJob() throws InterruptedException {

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        navigateTabsForJob(jobID, "Distribution");
        JobDetails.distributeJob();

        //Verify created job is in "Sourcing" state
        assertEquals(JobDetails.getJobStatus(),"Sourcing");
    }

    @Test(dependsOnMethods = "distributeJob", description = "Vendor to submit candidate to the above created job", priority = 4)
    public void submitCandidate() throws InterruptedException {

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");
        navigateTabsForJob(jobID, "Available Candidates");
        JobDetails.clickActionMenu("Submit Candidate");
        CandidateSubmission.enterCandidateSubmissionDetails();
        FilterTable.navigateToFirstItemInList();
        // Verify status of candidate is in Pending shortlist state
        //assertEquals(FilterTable.getColValueAgainst("", "Status"),"pending shortlist");
    }

    @Test(dependsOnMethods = "submitCandidate", description = "MSP to shortlist candidate", priority = 5)
    public void shortlistCandidate() throws InterruptedException {

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        navigateTabsForJob(jobID, "Submitted Candidates");
        // Navigate to candidate and shortlist
        FilterTable.navigateToFirstItemInList();
        JobDetails.shortlist_candidate();
        // Verify status of candidate is in Submitted state
        //assertEquals(FilterTable.getColValueAgainst("", "Status"),"submitted");
    }

    @Test(dependsOnMethods = "shortlistCandidate", description = "Hiring Manager to schedule interview for candidate", priority = 6)
    public void createOffer(String jobID) throws InterruptedException {

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        navigateTabsForJob(jobID, "Submitted Candidates");
        JobDetails.clickActionMenu("Create Offer");
        NewOffer.clickCreateOfferBtn();
        NewOffer.createNewOffer();
        //Verify created offer is in "Pending Approval" state
        assertEquals(JobDetails.getOfferStatus(),"Pending Approval");
    }

    @Test(dependsOnMethods = "createOffer", priority = 7)
    public void offerApproval() throws InterruptedException {
        //public void offerApproval(String jobID, String...approvers) throws InterruptedException {
        int approverCounter = 0;
        for(String approver : approvers){
            approverCounter++;
            // Login with Approver to approve the above created job
            lg.login("https://preprod-app.simplifyvms.com/", getEmail(approver), getPassword(approver));
            navigateTabsForJob(jobID, "Offers");
            FilterTable.navigateToFirstItemInList();
            JobDetails.viewApprovalNotification();
            JobDetails.approveOffer(approver);

            //Logout from Job Manager login
            if(approverCounter < approvers.length)
                lg.logout();
        }

        //Verify created offer is in "Released" state
        assertEquals(JobDetails.getOfferStatus(),"Released");
    }

    @Test(dependsOnMethods = "offerApproval", description = "Vendor to accept the above created offer", priority = 8)
    public void acceptOffer() throws Exception {

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");
        navigateTabsForJob(jobID, "Offers");
        //FilterTable.navigateFromActionMenu("Accept Offer");
        JobDetails.clickActionMenu("Accept Offer");
        SidePage.accept("Accept Offer");
        // Verify status of Candidate offer is 'Accepted'
        FilterTable.navigateToFirstItemInList();
        OfferDetails.navigateTabs("Background Check");
        BackgroundCheck.completeCheck();

        //TODO
        // Complete Setup

        //TODO
        // Enter Timesheet
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        lg.logout();
    }

    private String getEmail(String username){
        String email = null;
        if(username.equals("Connor Hm"))
            email = "avik.mistry+connor@simplifyvms.com";
        if(username.equals("Andy Hm"))
            email = "avik.mistry+andy@simplifyvms.com";
        if(username.equals("Kirthika Muthukumaran"))
            email = "simplifyqa+muthukumaran@simplifyvms.com";
        if(username.equals("John Paquette"))
            email = "simplifyqa+paquette@simplifyvms.com";
        return email;
    }

    private String getPassword(String username){
        String password = null;
        if(username.equals("Connor Hm") | username.equals("Andy Hm") | username.equals("Kirthika Muthukumaran") | username.equals("John Paquette"))
            password = "Demo@1234";
        return password;
    }

    private void navigateTabsForJob(String jobID, String...tabs) throws InterruptedException {
        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Approval");
    }
}
