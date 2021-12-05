package org.example;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.addListener(tla);

        XmlSuite loginSuite = new XmlSuite();
        loginSuite.setName("The Login Suite");

        XmlTest first_test = new XmlTest();
        first_test.setName("Some login check");
        first_test.setSuite(loginSuite);

        List<XmlClass> fistlogin_classes = new ArrayList<>();
        fistlogin_classes.add(new XmlClass("org.example.tests.Job5_1_Test"));

        //List<String> suites = Lists.newArrayList();
        //suites.add(".//abc.xml");
        //testng.setTestSuites(suites);
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(loginSuite);
        testng.setXmlSuites(suites);
        testng.run();
    }

}
