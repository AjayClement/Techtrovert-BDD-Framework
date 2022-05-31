package com.hsb.automation.runners;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import com.testauto.utility.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

//import com.testauto.utility.BrowserUtility;
//import com.testauto.utility.DriverFactory;
//import com.testauto.utility.PropertiesFileReader;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources",
glue = {"com.testauto.stepdef"},
tags="@testB and @Regression",
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "junit:test-results/reports/JunitReport.xml"})

public class RunScenarios extends AbstractTestNGCucumberTests{

        private static final Logger log =  LogManager.getLogger(RunScenarios.class);


        @BeforeSuite
        public void beforeSuite(ITestContext testContext){
             String suiteName = testContext.getCurrentXmlTest().getSuite().getName();
             log.info(TestRunUtil.getSuiteNameState(suiteName, "BeforeSuite"));
             DriverManager.killDriver(CommonPropertyManager.getBrowser());
             FileUtil.deleteFileAndDirectories(CommonPropertyManager.getTestResultsDir(), Arrays.asList(".xml", ".txt"));
         }

        @Parameters({"browsertype"})
        @BeforeTest
        public void beforeTestSetUp(ITestContext context, @Optional("") String browsertype){
           // TestContext testContext = new TestContext();
            log.info(TestRunUtil.getTestNameState(context.getName(),"BeforeTest"));
            CommonPropertyManager.setBrowser(
                    System.getProperty("browsertype") == null ?
                            browsertype : System.getProperty("browsertype")
            );
        }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }

    @AfterTest
    public void afterTest(ITestContext context){
            log.info(TestRunUtil.getTestNameState(context.getName(),"AfterTest"));
    }

    @AfterSuite
    public void afterSuite(ITestContext context){
        log.info(TestRunUtil.getSuiteNameState(context.getCurrentXmlTest().getSuite().getName(),"AfterSuite"));
    }

}
