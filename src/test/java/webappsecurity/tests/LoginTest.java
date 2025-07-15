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
        loginPage = homePage.clickOnSignInButton();
    }

    @DataProvider(name="user")
    public Object[][] getValidUser() {
        return new Object[][] { {"username", "password"} };
    }

    @Test(priority=1, dataProvider = "user")
    public void userCanLogInWithValidCredentials(String username, String password) {
        HomePage homePage = loginPage.login(username, password);
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), homePage.getCompleteUrl(), "URLs do not match!");
        Assert.assertEquals(homePage.getUsername(), username, "Username from login doesn't match page on home page!");
    }
}
