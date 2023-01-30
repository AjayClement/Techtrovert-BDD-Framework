package com.testauto.stepdef;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testauto.pages.AmazonLandingPage;
import com.testauto.utility.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.xml.datatype.Duration;
import java.util.concurrent.TimeUnit;

public class AmazonDemoSteps extends BaseSteps {

    private AmazonLandingPage amazonLandingPage;

    public AmazonDemoSteps(TestContext context) {
        super(context);
        amazonLandingPage = new AmazonLandingPage(context);
    }
    @When("user clicks on hamburger menu")
    public void user_clicks_on_hamburger_menu() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        amazonLandingPage.clickHamburgerIcon();
    }

    @Then("the menu is displayed")
    public void the_menu_is_displayed() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        softAssert.assertTrue(amazonLandingPage.verifyMenuIsDisplayed()
                ,"Menu is not displayed");
        softAssert.assertAll();
    }

}
