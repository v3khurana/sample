package org.example;

//import static org.junit.Assert.assertTrue;

//import org.junit.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.creditdatamw.zerocell.Reader;
import org.example.candidate.NewCandidate;
import org.example.job.*;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class Job5_1_Test
{
    ExtentReports extent = new ExtentReports();
    //final File CONF = new File("config/spark-config.json");
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark1234.html");
    ExtentTest test;
    Login lg = new Login();
    //Login lg = null;
    String jobID ="AMFAM MSP-JB-1873";
    String[] approvers = {"Kirthika Muthukumaran", "John Paquette"};
    String[] vendors = {"1INSURER INC"};
    //HashMap<String, HashMap<String, String>> createdCandidates = new HashMap<>();
    List<String> createdCandidatesId = new ArrayList<>();
    //private String uniqueKey;
    private String uniqueId="CA2012473";

    @BeforeSuite
    public void beforeSuite(){
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");
    }

    @BeforeTest
    public void beforeTest(){
        test = extent.createTest("Job 5_1");
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        BasePage.logger = test.createNode(m.getName());
        //lg = new Login();
    }

    /*
    @Test(dataProvider = "getData", dataProviderClass = DataProviders.class, description = "MSP to create/clone a job", priority = 1)
    //@Test(priority = 1)
    //@Parameters({"Environment", "Client"})
    public void createNewJob1(TestData data) throws InterruptedException {
    //public void createNewJob1(String Environment, String Client) throws InterruptedException {
        //System.out.println("Printing "+Environment + " : " + Client);

        System.out.println("Exceution of Test Case "+data.getTestcaseId());
        System.out.println(data.getBrowserName());
        System.out.println(data.getExecutionLocation());



        //AccountSetup.setup("","Demo@1234");

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        Sidebar.navigate("Job", "Create Job");

        Thread.sleep(8000);

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
    //@Test(priority = 2)
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
    //@Test(priority = 3)
    public void distributeJob() throws InterruptedException {

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        navigateTabsForJob(jobID, "Distribution");
        JobDetails.distributeJob(vendors);

        //Verify created job is in "Sourcing" state
        assertEquals(JobDetails.getJobStatus(),"Sourcing");
    }




    @Test(dependsOnMethods = "distributeJob", priority = 4)
    //@Test(priority = 1)
    public void createCandidate() throws Exception {
        // Login with Vendor to accept the above created offer
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");

        //Creation of candidates
        //for(int i = 1; i<=3; i++) {
            Sidebar.navigate("Candidates","Create Candidates");

            String firstName="candidateFirstName";
            String lastName="candidateLastName";
            String dob=normalizeDt(getRandomNumber(1, 28)+"/"+ getRandomNumber(1, 12)+"/"+ 2016);
            System.out.println("Date of Birth "+dob);
            String country = "Taiwan";
            String nationalID = String.valueOf(getRandomNumber(100, 999));
            String emailAddr = "varun.khurana"+getRandomNumber(100, 9999)+"@simplifyvms.com";
            String primaryAddr = "Bangalore";
            String education = "Bachelor of Technology Technology";

            NewCandidate.enterCandidateInfo(firstName, lastName, dob, country, nationalID, emailAddr, primaryAddr, education);
            uniqueId = firstName.substring(0, 2).toUpperCase() + dob.split("/")[0] + dob.split("/")[1] + nationalID;

            System.out.println("Unique id for candidate is "+uniqueId);
            createdCandidatesId.add(uniqueId);
        //}
    }


    @Test(dependsOnMethods = "createCandidate", priority = 5)
    //@Parameters({"Environment", "Client"})
    //@Test(priority = 5)
    //public void submitCandidate(@Optional("Environment") String Environment, String client) throws Exception {
    //public void submitCandidate(String Environment, String client) throws Exception {
    public void submitCandidate() throws Exception {
        //System.out.println("Tests to run for "+ Environment + " : "+ client);
        // Login with Vendor to accept the above created offer
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");

        //Submission of candidates
        //for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Available Candidates");
            //JobList.searchByJobTitleOrJobId(uniqueId);
            CandidateList.searchByNameOrId(uniqueId);
            // Submit Candidate
            JobDetails.clickActionMenu("Submit Candidate");
            CandidateSubmission.enterCandidateSubmissionDetails();
        //}
    }

    @Test(dependsOnMethods = "submitCandidate", priority = 6)
    public void shortlistCandidate() throws Exception {
        // Login with MSP to shortlist candidate
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");

        //Submission of candidates
        //for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Submitted Candidates");
            CandidateList.searchByNameOrId(uniqueId);
            // Navigate to candidate and shortlist
            FilterTable.navigateToFirstItemInList();
            JobDetails.shortlist_candidate();
        //}
    }



    @Test(dependsOnMethods = "shortlistCandidate", priority = 7)
    //@Test(priority = 7)
    public void interviewCandidate() throws Exception {

        String interviewType = "Virtual";
        String interviewLocation = "Microsoft teams";
        String interviewDuration = "15 Minutes";
        String interviewScheduledTime = "Today's date";
        //String interviewTimeSlot = "06:45 AM-07:00 AM";
        String interviewTimeSlot = "09:45 AM-10:00 AM";
        boolean vendorAcceptsInterview = true;

        // Login with Hiring Manager to interview candidate
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+muthukumaran@simplifyvms.com","Demo@1234");

        //Interview of candidates
        //for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Submitted Candidates");
            CandidateList.searchByNameOrId(uniqueId);
            JobDetails.clickActionMenu("Schedule Interview");
            NewInterview.scheduleInterview(interviewType, interviewLocation, interviewDuration, interviewScheduledTime, interviewTimeSlot);
        //}
    }
*/
/*

    //@Test(dependsOnMethods = "interviewCandidate", priority = 8)
    @Test(priority = 8)
    public void acceptInterview() throws Exception {
        // Login with Vendor to accept interviews
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+insurerinc@simplifyvms.com","Demo@1234");

        //Interview Acceptance of candidates
        //for(String uniqueId : createdCandidatesId) {
            Sidebar.navigate("Job","View All Job");
            JobList.searchByJobTitleOrJobId(jobID);
            JobList.navigateToJobDetails();
            JobDetails.navigateTabs("Interviews");
            //CandidateList.searchByNameOrId(uniqueId);
            // Navigate to candidate and accept interview
            FilterTable.navigateToFirstItemInList();
            JobDetails.acceptInterview();
        //}
    }

    @Test(dependsOnMethods = "acceptInterview", priority = 9)
    public void markInterviewAsCompleted() throws Exception {
        // Login with Hiring Manager to accept interviews
        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+muthukumaran@simplifyvms.com","Demo@1234");

        Sidebar.navigate("Job","View All Job");
        JobList.searchByJobTitleOrJobId(jobID);
        JobList.navigateToJobDetails();
        JobDetails.navigateTabs("Interviews");
        //CandidateList.searchByNameOrId(uniqueId);
        // Navigate to candidate and accept interview
        FilterTable.navigateToFirstItemInList();
        InterviewDetails.markAsComplete("Candidate is fit for the Job");
        //}
    }


*/
    //@Test(dependsOnMethods = "markInterviewAsCompleted", description = "Hiring Manager to schedule interview for candidate", priority = 10)
    @Test(description = "Hiring Manager to schedule interview for candidate", priority = 10)
    public void createOffer() throws InterruptedException {

        lg.login("https://preprod-app.simplifyvms.com/","simplifyqa+wethal@simplifyvms.com","Demo@1234");
        navigateTabsForJob(jobID, "Submitted Candidates");
        JobDetails.clickActionMenu("Create Offer");
        //NewOffer.clickCreateOfferBtn();
        NewOffer.createNewOffer();
        //Verify created offer is in "Pending Approval" state
        assertEquals(JobDetails.getOfferStatus(),"Pending Approval");
    }

    @Test(dependsOnMethods = "createOffer", priority = 11)
    //@Test(priority = 11)
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

    @Test(dependsOnMethods = "offerApproval", description = "Vendor to accept the above created offer", priority = 12)
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

        // Complete Setup
        String url = OutlookReader1.getSetupURL();
        String password = "Demo@1234";
        AccountSetup.setup(url, password);
    }

    @Test(dependsOnMethods = "acceptOffer", priority = 13)
    public void enterTimesheet() throws InterruptedException {
        lg.login("https://preprod-app.simplifyvms.com/","varun.khurana+67676@simplifyvms.com","Demo@1234");
        String assignment = "myNewJobTemp(AMF-AM-568) Active";
        String workPeriod = "11/22/2021 - 11/24/2021";
        HashMap<String,String[]> timeSheetData = new HashMap<>();
        timeSheetData.put("22", new String[]{"09:20 AM", "5:34 PM"});
        timeSheetData.put("23", new String[]{"09:18 AM", "6:00 PM"});
        timeSheetData.put("24", new String[]{"09:00 AM", "8:30 PM"});
        AccountSetup.createTimeSheet(assignment, workPeriod, timeSheetData);
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        try {
            lg.logout();
        }catch(Exception e){
            System.out.println("Unable to logout");
        }
    }

    @AfterTest
    public void afterTest(){
        extent.flush();
        //lg.closeBrowser();
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
        JobDetails.navigateTabs(tabs[0]);
    }

    private static int getRandomNumber(int ll, int hl){
        return (int)(Math.random()*(hl-ll+1)+ll);
    }

    private static String normalizeDt(String dt){
        String[] parts = dt.split("/");
        String newDt = new String();
        for(String i : parts) {
            if(Integer.parseInt(i) >= 10)
                newDt = newDt + "/" + i;
            else
                newDt =  newDt + "/" + "0" + i;
        }
        newDt = newDt.substring(1);
        return newDt;

    }
}
