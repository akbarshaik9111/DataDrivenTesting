package com.akbar.rahul;

import java.io.IOException;
import java.util.List;

public class _02_SampleData {
    public static void main(String[] args) throws IOException {
        _01_ExcelDemo data = new _01_ExcelDemo();
        List<String> li = data.getData("Purchase");
        System.out.println(li.get(0));
        System.out.println(li.get(1));
        System.out.println(li.get(2));
        System.out.println(li.get(3));
    }
}
