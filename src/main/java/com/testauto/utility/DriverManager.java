package com.testauto.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Set;

/**
 * This class will be used instead of DriverFactory.class, SeleniumActions.class
 * and BrowserUtility.class
 */

public class DriverManager {
    public static WebDriver driver;
    public static WebDriverWait wait = null;
    public static JavascriptExecutor executor = null;
    public static Actions actions =  null;
    public static Logger log = LogManager.getLogger(DriverManager.class);
    private static boolean status;
    public static int DELAYTIME = 3;
    public static int Maxtimetowait = 10;
    /**
     * This method will create driver for respective browser
     * @param driverType - Type of the driver needs to be created
     * @return WebDriver based on type of the driver
     */



    public WebDriver getDriver(DriverType driverType, String url){
        try {
            switch(driverType){
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver =  new FirefoxDriver();
                    break;
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver =  new ChromeDriver();
                    // When running in test jenkins, use the following lines

			/*	System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-web-security");
				chromeOptions.addArguments("--ignore-urlfetcher-cert-requests");
				chromeOptions.addArguments("--disable-renderer-backgrounding");
				chromeOptions.addArguments(“--disable-infobars");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited
				resource problems chromeOptions.addArguments("--no-sandbox"); // Bypass OS
				security model chromeOptions.addArguments("window-size=1200, 800");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome"); chromeOptions.merge(cap); driver = new
				ChromeDriver(chromeOptions) ;
			*/

                    break;

                case IE:
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case HEADLESS:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    driver = new ChromeDriver(options);
                    break;

                default:
                    log.error("No driver found for the given driver type: {}", driverType);
                    break;
            }
            driver.manage().window().maximize();
            driver.get(url);
         //   wait = new WebDriverWait(driver, 120);
            actions = new Actions(driver);
            executor = ((JavascriptExecutor) driver);

        }catch (Exception e){
            log.error("Driver is not initialized for driver type: {}; Exception: {}", driverType, e.getMessage());
        }
        return driver;
    }

    public void navigateToUrl(String Url) {
        driver.get(Url);
    }

    public void closeBrowser(){
        driver.close();
    }

    public void closeAllBrowserWindows(){
        Set<String> windowHandles = driver.getWindowHandles();
        for(String window : windowHandles){
            driver.switchTo().window(window);
            driver.close();
        }
    }

    public void quitBrowser(){
        driver.quit();
    }

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public static void killDriver(DriverType driverType){
        String driverExe = null;
        switch (driverType){
            case FIREFOX:
                driverExe = "geckodriver.exe";
                break;
            case CHROME:
            case HEADLESS:
                driverExe = "chromedriver.exe";
                break;
            case EDGE:
                driverExe = "MicrosoftWebDriver.exe";
                break;
            case IE:
                driverExe = "IEDriverServer.exe";
                break;
            default:
                log.error("No driver found for drivertype '" + driverType + "'");
                break;
        }
        String command = "taskkill /F /IM " + driverExe;
        try{
            Runtime.getRuntime().exec(command);
        }catch (IOException e){
            log.error("unable to kill driver '" + command, e.getMessage());
        }
    }
}
