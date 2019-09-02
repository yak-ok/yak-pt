package com.yak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Starter {
    public static void main(String[] args) throws InterruptedException {

        /*
        WebDriver chromeDriver = new ChromeDriver();    //Chrome浏览器

        //System.setProperty("webdriver.firefox.marionette","D:\\driver\\geckodriver.exe");
        WebDriver firefoxDriver = new FirefoxDriver();   //Firefox浏览器

        WebDriver edgeDriver = new EdgeDriver();      //Edge浏览器

        WebDriver ieDriver = new InternetExplorerDriver();  // Internet Explorer浏览器

        WebDriver operaDriver = new OperaDriver();     //Opera浏览器

        //WebDriver phantomJSDriver = new PhantomJSDriver();   //PhantomJS
        */

        System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //打开百度
        driver.get("https://www.baidu.com");

        //搜索医院
        WebElement searchIn = driver.findElement(By.id("kw"));
        searchIn.clear();
        searchIn.sendKeys("医院");

        //点击查询
        WebElement searchBt = driver.findElement(By.id("su"));
        searchBt.click();
        Thread.sleep(2000);

        //选择一条广告，点击查看
        driver.findElement(By.id("4001")).click();
        Thread.sleep(2000);

        //关闭窗口
        driver.close();
    }
}
