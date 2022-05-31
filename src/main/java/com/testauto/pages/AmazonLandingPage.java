package com.testauto.pages;

import com.testauto.utility.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonLandingPage extends BasePage{

    public AmazonLandingPage(TestContext context){
        super(context);
    }

    @FindBy(xpath = "descendant::a[@id='nav-hamburger-menu']")
    private WebElement hamburgerMenuIcon;
    //

    @FindBy(xpath = "descendant::div[@id='hmenu-customer-profile']")
    private WebElement menuProfile;

    public void clickHamburgerIcon(){
        hamburgerMenuIcon.click();
        log.info("Hamburger Menu is clicked");
    }

    public boolean verifyMenuIsDisplayed(){
        return menuProfile.isDisplayed();
    }

}
