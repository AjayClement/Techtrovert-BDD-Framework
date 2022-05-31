package com.testauto.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

public class CommonPropertyManager {
    private static final Logger log = LogManager.getLogger(CommonPropertyManager.class);
    private static Properties commonProperties = new Properties();
    private static final String RUN_PROPERTIES_FILE = "run-config.properties";

    static {
        try(InputStream resourceStream = CommonPropertyManager.class.getClassLoader()
                .getResourceAsStream(RUN_PROPERTIES_FILE)){
            commonProperties.load(resourceStream);
        } catch (IOException e) {
            log.error("'Run-Config' Properties file not found in path", e.getMessage());
        }
    }

    public static DriverType getBrowser(){
        String browser = commonProperties.getProperty("browser");
        DriverType driverType;
        switch (browser.toLowerCase()) {
            case "firefox":
                driverType = DriverType.FIREFOX;
                break;
            case "chrome":
                driverType = DriverType.CHROME;
                break;
            case "edge":
                driverType = DriverType.EDGE;
                break;
            case "ie":
                driverType = DriverType.IE;
                break;
            default:
                driverType = DriverType.CHROME;
                break;
        }
          return driverType;
        }

    public static String getTestResultsDir() {
        return commonProperties.getProperty("testresults.dir");
    }

    public static String getTestReportsDir() {
        return commonProperties.getProperty("reports.dir");
    }

    public static String getScreenshotsDir() {
        return commonProperties.getProperty("screenshots.dir");
    }

    public static String getScreenshotsFormat() {
        return commonProperties.getProperty("screenshots.filetype");
    }

    public static void setBrowser(String browser){
        if(!browser.isEmpty()){
            commonProperties.setProperty("browser", browser.toUpperCase());
            try {
                commonProperties.store(new StringWriter(),"Browser type is stored in common config property");
                log.info("Browser type ' " + browser + "' saved successfully in common config property");
            }catch (Exception e){
                log.error("Not able to store the browser type '" + browser + "' in common config properties" + e.getMessage());
            }
        }
    }

    public static Properties loadProperty() {

        Properties props =  new Properties();
        try{
            //props.load(new FileInputStream("resources/browser-config.properties"));
            props.load(new FileInputStream("src/main/resources/login.properties"));
            //props.load(new FileInputStream("resources/config.properties"));
        }catch(IOException e){
            log.error("unable to load requires properties" + e.getMessage());
        }
        return props;
    }

    }


