package com.yak.factory;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Class.*;

public class DriverFactory {
    static Properties init() {
        // 从properties文件中读取shortName对应的完整包名
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("driver.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static WebDriver getDiver(String driverType) {
        WebDriver webDriver = null;
        try {
            String fullName = DriverFactory.init().getProperty(driverType);
            webDriver = (WebDriver) forName(fullName).getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return webDriver;
    }
}
