package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Program {

    private void testRunner(Map<String, String> testngParams) throws IOException {

        //Create an instance on TestNG
        TestNG testNG = new TestNG();

        //Create an instance of XML Suite and assign a name for it.
        XmlSuite suite = getXmlSuite();

        //Create an instance of XmlTest and assign a name for it.
        XmlTest test = getXmlTest(suite);

        //Add any parameters that you want to set to the Test.
        test.setParameters(testngParams);

        //Create a list which can contain the classes that you want to run.
        List<XmlClass> classes = getXmlClasses();

        //Assign that to the XmlTest Object created earlier.
        test.setXmlClasses(classes);

        //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> tests = new ArrayList<>();
        tests.add(test);

        //add the list of tests to your Suite.
        suite.setTests(tests);

        //Add the suite to the list of suites.
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        //Set the list of Suites to the testNG object you created earlier.
        testNG.setXmlSuites(suites);

        File file = new File("TestNG_dynamic.xml");
        System.out.println("file"+file);

        FileWriter writer = new FileWriter(file);
        writer.write(suite.toXml());
        writer.close();

        //invoke run() - this will run your class.
        testNG.run();

    }

    private XmlSuite getXmlSuite() {
        XmlSuite suite = new XmlSuite();
        suite.setName("Test Suite");
        return suite;
    }

    private XmlTest getXmlTest(XmlSuite suite) {
        XmlTest test = new XmlTest(suite);
        test.setName("C:/Users/VarunKhurana/qa/trial1");
        //test.setParallel(XmlSuite.ParallelMode.METHODS);
        return test;
    }

    private List<XmlClass> getXmlClasses() {
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("org.example.Job5_1_Test"));
        return classes;
    }

    public static void main(String[] args) throws IOException {

        Program program = new Program();
        Map<String,String> params = new HashMap<>();
        /*
        params.put("browserName", "Firefox");
        params.put("remoteUrl", "");
        params.put("domain", "http://www.google.com");
         */
        //params.put("Environment", "Pre-prod");
        //params.put("Client", "AMFAM");
        params.put("Environment", args[0]);
        params.put("Client", args[1]);
        program.testRunner(params);
    }
}
