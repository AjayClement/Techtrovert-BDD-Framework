package com.testauto.pages;

import com.testauto.utility.DriverManager;
import com.testauto.utility.TestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class BasePage {

    protected TestContext testContext;
    protected WebDriver driver;
    protected DriverManager driverManager;
    protected SoftAssert softAssert;
    protected Logger log;

    public BasePage(TestContext context){
        this.testContext =  context;
        driver = testContext.getWebDriver();
        PageFactory.initElements(driver, this);
        driverManager = testContext.getDriverManager();
        softAssert = testContext.getSoftAssertObject();
        log = LogManager.getLogger(this.getClass());
    }

}
