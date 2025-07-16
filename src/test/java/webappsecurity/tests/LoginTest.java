package webappsecurity.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import webappsecurity.base.BaseTest;
import webappsecurity.pages.HomePage;
import webappsecurity.pages.LoginPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void methodSetup() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        loginPage = homePage.getNavbar().clickOnSignInButton();
    }

    @DataProvider(name="user")
    public Object[][] getValidUser() {
        return new Object[][] {
                {"username", "password"},
        };
    }
    @Test(priority=1, dataProvider = "user")
    public void userCanLogInWithValidCredentials(String username, String password) {
        HomePage homePage = loginPage.login(username, password);
        homePage.navigateTo();
        Assert.assertEquals(driver.getCurrentUrl(), homePage.getCompleteUrl(), "URLs do not match!");
        Assert.assertEquals(homePage.getNavbar().getUserName(), username, "Username from login doesn't match page on home page!");
    }
}
