package com.akbar.sdet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class _05_ReadingDataFromPropertiesFile {
    public static void main(String[] args) throws IOException {

        // location of properties file
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\data\\global.properties");

        // Loading properties file
        Properties props = new Properties();
        props.load(fis);

        // Reading data from properties file
        String app_url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String orderId = props.getProperty("orderId");
        String transactionId = props.getProperty("transactionId");

        System.out.println(app_url+"\t"+username+"\t"+password+"\t"+orderId+"\t"+transactionId);

        // Reading all the keys from properties file
        Set<String> propertyNames = props.stringPropertyNames();
        for(String name : propertyNames) {
            System.out.println(name);
        }

        // Reading all the keys from properties file
        Set<Object> propertyKeys = props.keySet();
        System.out.println(propertyKeys);

        // Reading all teh values from properties file
        Collection<Object> propertyValues = props.values();
        System.out.println(propertyValues);

        fis.close();
    }
}
