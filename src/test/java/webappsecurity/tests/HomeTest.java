package webappsecurity.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webappsecurity.base.BaseTest;
import webappsecurity.pages.HomePage;
import webappsecurity.pages.LoginPage;

public class HomeTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    public void methodSetup() {
        homePage = new HomePage(driver);
    }

    @Test(priority=1)
    public void checkIfUserCanOpenLoginPage() {
        LoginPage loginPage = homePage.clickOnSignInButton();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, loginPage.getCompleteUrl(), "URLs do not match!");
    }


}
