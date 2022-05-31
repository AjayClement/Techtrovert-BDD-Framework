package com.testauto.stepdef;

import com.testauto.pages.AmazonLandingPage;
import com.testauto.utility.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonDemoSteps extends BaseSteps {

    private AmazonLandingPage amazonLandingPage;

    public AmazonDemoSteps(TestContext context) {
        super(context);
        amazonLandingPage = new AmazonLandingPage(context);
    }
    @When("user clicks on hamburger menu")
    public void user_clicks_on_hamburger_menu() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        amazonLandingPage.clickHamburgerIcon();
    }

    @Then("the menu is displayed")
    public void the_menu_is_displayed() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        softAssert.assertTrue(amazonLandingPage.verifyMenuIsDisplayed()
                ,"Menu is not displayed");
        softAssert.assertAll();
    }

}
