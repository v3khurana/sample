package org.example;

import com.creditdatamw.zerocell.annotation.Column;

public class TestData {

    public String getTestcaseId() {
        return testcaseId;
    }

    public String getExecutionLocation() {
        return executionLocation;
    }

    public String getBrowserName() {
        return browserName;
    }

    @Column(name = "TC_ID", index = 0)
    private String testcaseId;

    @Column(name = "ExecutionLocation", index = 1)
    private String executionLocation;

    @Column(name = "Browser", index = 2)
    private String browserName;

    @Override
    public String toString() {
        return "TestData{" +
                "testcaseId='" + testcaseId + '\'' +
                ", executionLocation='" + executionLocation + '\'' +
                ", browserName='" + browserName + '\'' +
                '}';
    }
}
