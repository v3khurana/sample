package org.example;

//import static org.junit.Assert.assertTrue;

//import org.junit.Test;

import org.example.candidate.NewCandidate;
import org.example.job.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class Job5
{
    Login lg = new Login();
    String jobID = "";
    String[] approvers = {"Kirthika Muthukumaran", "John Paquette"};
    HashMap<String, HashMap<String, String>> createdCandidates = new HashMap<>();
    List<String> createdCandidatesId = new ArrayList<>();

    @Test(description = "MSP to create/clone a job", priority = 1)
    public void createNewJob1() throws InterruptedException {

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

    @Test
    public void createCandidates() throws Exception {
        // Login with Vendor to accept the above created offer
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");

        //Creation of candidates
        for(int i = 1; i<=3; i++) {
            Sidebar.navigate("Candidates","Create Candidates");

            String firstName="candidateFirstName";
            String lastName="candidateLastName";
            String dob=getRandomNumber(1, 28)+"/"+ getRandomNumber(1, 12)+"/"+ 2016;
            String country = "Taiwan";
            String nationalID = String.valueOf(getRandomNumber(100, 999));
            String emailAddr = "varun.khurana"+getRandomNumber(100, 9999)+"@simplifyvms.com";
            String primaryAddr = "Bangalore";
            String education = "Bachelor of Technology Technology";
            String interviewType = "Virtual";
            String interviewLocation = "Microsoft teams";
            String interviewDuration = "15 Minutes";
            String interviewScheduledTime = "Today's Date";
            String interviewTimeSlot = "06:45 AM-07:00 AM";
            boolean vendorAcceptsInterview = true;

            NewCandidate.enterCandidateInfo(firstName, lastName, dob, country, nationalID, emailAddr, primaryAddr, education);
            String uniqueKey = firstName.substring(0, 2) + dob.split("/")[1] + dob.split("/")[0] + nationalID;
            //List<String> candidateProps = new ArrayList<>();
            //candidateProps.add("");
            HashMap candidateProps = new HashMap();
            candidateProps.put("Interview Type", interviewType);
            candidateProps.put("Duration", interviewDuration);
            candidateProps.put("Scheduled Time", interviewScheduledTime);
            candidateProps.put("Vendor Accepts", vendorAcceptsInterview);
            candidateProps.put("Interview Location", interviewLocation);
            candidateProps.put("Interview Date", interviewScheduledTime);
            candidateProps.put("Interview Time Slots", interviewTimeSlot);
            createdCandidates.put(uniqueKey, candidateProps);
            createdCandidatesId.add(uniqueKey);
        }
    }

    @Test(dependsOnMethods = "createCandidates")
    public void submitCandidates() throws Exception {
        // Login with Vendor to accept the above created offer
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");

        //Submission of candidates
        for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Available Candidates");
            JobList.searchByJobTitleOrJobId(uniqueId);
            // Submit Candidate
            JobDetails.clickActionMenu("Submit Candidate");
            CandidateSubmission.enterCandidateSubmissionDetails();
        }
    }

    @Test(dependsOnMethods = "submitCandidates")
    public void shortlistCandidates() throws Exception {
        // Login with MSP to shortlist candidate
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");

        //Submission of candidates
        for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Submitted Candidates");
            JobList.searchByJobTitleOrJobId(uniqueId);
            // Navigate to candidate and shortlist
            FilterTable.navigateToFirstItemInList();
            JobDetails.shortlist_candidate();
        }
    }

    @Test(dependsOnMethods = "shortlistCandidates")
    public void interviewCandidates() throws Exception {
        // Login with Hiring Manager to interview candidate
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+muthukumaran@simplifyvms.com","Demo@1234");

        //Interview of candidates
        for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Submitted Candidates");
            JobList.searchByJobTitleOrJobId(uniqueId);
            JobDetails.clickActionMenu("Schedule Interview");

            String interviewType = createdCandidates.get(uniqueId).get("interviewType");
            String location = createdCandidates.get(uniqueId).get("Interview Location");
            String duration = createdCandidates.get(uniqueId).get("Duration");
            String interviewDate = createdCandidates.get(uniqueId).get("Interview Date");
            String timeSlots = createdCandidates.get(uniqueId).get("Interview Time Slots");
            NewInterview.scheduleInterview(interviewType, location, duration, interviewDate, timeSlots);
        }
    }

    @Test(dependsOnMethods = "interviewCandidates")
    public void acceptInterviews() throws Exception {
        // Login with Vendor to accept interviews
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");

        //Interview Acceptance of candidates
        for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Interviews");
            JobList.searchByJobTitleOrJobId(uniqueId);
            // Navigate to candidate and accept interview
            FilterTable.navigateToFirstItemInList();
            JobDetails.acceptInterview();
        }
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

    private static int getRandomNumber(int ll, int hl){
        Random random = new Random();
        random.ints(1, ll, hl);
        return 0;
    }
}
