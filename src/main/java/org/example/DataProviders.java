package org.example;

import org.example.ExcelReader;
import org.example.TestData;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class DataProviders {

    @DataProvider(parallel = true)
    public static Object[][] getData(Method m) {
        List<TestData> collect = ExcelReader.getData()
                .stream()
                .filter(e -> e.getTestcaseId().equalsIgnoreCase(m.getName()))
                .collect(Collectors.toList());

        Object[][] data = new Object[collect.size()][1];
        for (int i = 0; i < collect.size(); i++) {
            data[i][0] = collect.get(i);
        }

        return data;
    }

}
