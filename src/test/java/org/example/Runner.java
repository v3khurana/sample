package org.example;

import com.creditdatamw.zerocell.Reader;

import java.io.File;
import java.util.List;

public class Runner {

    public static void main(String[] args){

        List<TestData> data = Reader.of(TestData.class)
                .from(new File("testData.xlsx"))
                .sheet("ExecutionProps")
                .skipHeaderRow(true)
                .list();

        data.forEach(System.out::println);

    }

}
