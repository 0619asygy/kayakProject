package com.kayakProject.utilities.utilities;

import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    private static Logger logger = Logger.getLogger(Driver.class);
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static String userName = "ayseturk1";
    private static String accessKey = "yA2x8ANmgHFyEjSspFKg";
    private static final String URL = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";



    private Driver() {

    }

    public static WebDriver getDriver() {
        //if this thread doesn't have a web driver yet - create it and add to pool
        if (driverPool.get() == null) {
            System.out.println("TRYING TO CREATE DRIVER");
            // this line will tell which browser should open based on the value from properties file
            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigurationReader.getProperty("browser") : browserParamFromEnv;
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":

                    try {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability("platform", Platform.ANY);
                        driverPool.set(new RemoteWebDriver(new URL("http://ec2-100-24-5-133.compute-1.amazonaws.com:4444/wd/hub"),chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote_firefox":

                    try {
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                        driverPool.set(new RemoteWebDriver(new URL("http://ec2-100-24-5-133.compute-1.amazonaws.com:4444/wd/hub"),desiredCapabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "mobile_chrome":
                    try {
                        //chrome options are used to parametrize browser
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("-incognito");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
                        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
                        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
                        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "mobile_chrome_remote":
                    try {
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("browserName", "android");
                        caps.setCapability("device", "Samsung Galaxy S10");
                        caps.setCapability("realMobile", "true");
                        caps.setCapability("os_version", "9.0");
                        caps.setCapability("name", "VyTrack tests");
                        driverPool.set(new RemoteWebDriver(new URL(URL), caps));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "mobile_safari_remote":
                    try {
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("browserName", "safari");
                        caps.setCapability("device", "iPhone 11 Pro Max");
                        caps.setCapability("os_version", "13");
                        caps.setCapability("realMobile", "true");
                        caps.setCapability("name", "VyTrack tests");
                        driverPool.set(new RemoteWebDriver(new URL(URL), caps));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("Invalid browser name!");
            }

        }
        //return corresponded to thread id webdriver object
        return driverPool.get();
    }

    public static void close() {
        driverPool.get().quit();
        driverPool.remove();
    }

}