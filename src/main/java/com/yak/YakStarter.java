package com.yak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.yak.*.mapper")
public class YakStarter {

    public static void main(String[] args) {
        SpringApplication.run(YakStarter.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Czl启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

    public static void main2(String[] args) throws Exception {

        /*
        WebDriver chromeDriver = new ChromeDriver();    //Chrome浏览器

        //System.setProperty("webdriver.firefox.marionette","D:\\driver\\geckodriver.exe");
        WebDriver firefoxDriver = new FirefoxDriver();   //Firefox浏览器
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("network.proxy.type", 1);
        firefoxProfile.setPreference("network.proxy.http", (IPPORT.split(",")[0].split(":"))[0]);
        firefoxProfile.setPreference("network.proxy.http_port", Integer.parseInt((IPPORT.split(",")[0]).split(":")[1]));
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        WebDriver webDriver = new FirefoxDriver(firefoxOptions);

        WebDriver edgeDriver = new EdgeDriver();      //Edge浏览器

        WebDriver ieDriver = new InternetExplorerDriver();  // Internet Explorer浏览器

        WebDriver operaDriver = new OperaDriver();     //Opera浏览器

        //WebDriver phantomJSDriver = new PhantomJSDriver();   //PhantomJS

        System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //打开百度
        driver.get("https://www.baidu.com");

        String search_handle = driver.getWindowHandle();

        //搜索医院
        WebElement searchIn = driver.findElement(By.id("kw"));
        searchIn.clear();
        searchIn.sendKeys("医院");

        //点击查询
        WebElement searchBt = driver.findElement(By.id("su"));
        searchBt.click();
        Thread.sleep(2000);

        //获取第一条搜索结果的标题
        WebElement result = driver.findElement(By.xpath("//div[@id='content_left']/div/h3/a"));
        System.out.println(result.getText());

        //选择一条广告，点击查看
        driver.findElement(By.id("4001")).click();
        Thread.sleep(2000);

        //获取所有打开的页面
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(search_handle)) {
                //切换页面
                driver.switchTo().window(handle);
                Thread.sleep(2000);
                //关闭当前窗口
                driver.close();
            }
        }

        //添加cookie
        Cookie c1 = new Cookie("name", "key-aaaaaaa");
        Cookie c2 = new Cookie("value", "value-bbbbbb");

        WebDriver.Options manage = driver.manage();
        manage.addCookie(c1);
        manage.addCookie(c2);

        //获取所有cookie
        manage.getCookies();

        //获取指定cookie
        manage.getCookieNamed("name");

        Proxy proxy = new Proxy();
        String proxyIpAndPort = "127.0.0.1:8000";
        System.out.println(proxyIpAndPort);
        proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
        cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);

        System.setProperty("http.nonProxyHosts", "localhost");
        cap.setCapability(CapabilityType.PROXY, proxy);

        ChromeOptions chromeOptions = new ChromeOptions().merge(cap);

        //删除所有cookie
        manage.deleteAllCookies();
        driver.quit();
        */

    }

    //关闭窗口
}
