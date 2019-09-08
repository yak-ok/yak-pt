package com.yak;

import com.yak.db.AdDao;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Starter {
    private static final int port = 8080;
    private static final String CONTEXT = "/";

    public static Server createServer(int port) throws MalformedURLException, URISyntaxException {
        Server server = new Server();
        server.setStopAtShutdown(true); //JVM退出时关闭Jetty

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setReuseAddress(false); //重复启动Jetty居然报端口冲突
        server.setConnectors(new Connector[]{connector});

        WebAppContext webContext = new WebAppContext("src/main/webapp", CONTEXT);
        webContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        webContext.setResourceBase("src/main/webapp");
        webContext.setClassLoader(Starter.class.getClassLoader());
        server.setHandler(webContext);
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = Starter.createServer(Starter.port);
        try {
            server.stop();
            server.start();

            openWin();

            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openWin() throws Exception {
        String url = "http://localhost:8080";
        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = new URI(url);
            desktop.browse(uri);
        }
    }

    public static void main2(String[] args) throws Exception {
        Server server = new Server(8080);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        // Or ServletContextHandler.NO_SESSIONS
        context.setContextPath("/");
        server.setHandler(context);
        WebAppContext webContext = new WebAppContext("webapp", "/");
        webContext.setBaseResource(Resource.newResource(new URL(Starter.class.getResource("/webapp/WEB-INF"), ".")));
        webContext.setClassLoader(Starter.class.getClassLoader());

        server.start();
        server.join();

        AdDao.readyAd();

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
