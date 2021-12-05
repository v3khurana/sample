package org.example;

import com.creditdatamw.zerocell.Reader;

import java.io.File;
import java.util.List;

public final class ExcelReader {

    private ExcelReader(){}

    public static List<TestData> getData(){
        return data;
    }

    private static List<TestData> data = null;

    static {
        data = Reader.of(TestData.class)
                .from(new File("testData.xlsx"))
                .sheet("ExecutionProps")
                .skipHeaderRow(true)
                .list();
    }

}
