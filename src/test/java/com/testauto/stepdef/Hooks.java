package com.testauto.stepdef;

import com.testauto.utility.ScreenshotUtil;
import com.testauto.utility.TestContext;
import com.testauto.utility.TestRunUtil;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.junit.After;
import org.junit.Before;

public class Hooks extends BaseSteps {

    public Hooks(TestContext context){
        super(context);
    }

    @Before
    public void beforeScenario(Scenario scenario){

        log.info(TestRunUtil.getScenarioExecutionStartState(scenario));
    }

    @AfterStep
    public void afterStep(Scenario scenario){
        ScreenshotUtil.captureFullScreenshot(driver, scenario);
    }

    @After
    public void afterScenario(Scenario scenario){
        if(scenario.isFailed() || scenario.getStatus().toString().equalsIgnoreCase("UNDEFINED")){
            ScreenshotUtil.captureFullScreenshot(driver, scenario);
        }
        log.info(TestRunUtil.getScenarioExecutionEndState(scenario));
        driverManager.closeAllBrowserWindows();
        driverManager.quitBrowser();
    }

}
