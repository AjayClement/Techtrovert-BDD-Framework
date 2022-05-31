package com.testauto.utility;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class TestContext {

    private WebDriver driver;
    private DriverManager driverManager;
    private SoftAssert softAssert;

    public TestContext(){
        driverManager = new DriverManager();
        driver = driverManager.getDriver(CommonPropertyManager.getBrowser(),CommonPropertyManager.loadProperty().getProperty("app.url"));
     }

     public DriverManager getDriverManager(){
        return driverManager;
     }

     public WebDriver getWebDriver(){
        return driver;
     }

     public SoftAssert getSoftAssertObject(){
        return (softAssert == null) ? softAssert = new SoftAssert() :softAssert;
    }

}
