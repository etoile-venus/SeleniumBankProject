package webappsecurity.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webappsecurity.base.BaseTest;
import webappsecurity.pages.HomePage;
import webappsecurity.pages.LoginPage;

public class HomeTest extends BaseTest {
    private HomePage homePage;

    @BeforeClass
    public void cookiesSetup() {
        homePage = new HomePage(driver);
        LoginPage loginPage = homePage.getNavbar().clickOnSignInButton();
        loginPage.login("username", "password");
        driver.navigate().back();
        jSessionId = driver.manage().getCookieNamed("JSESSIONID");
    }

    @BeforeMethod
    public void methodSetup() {
        homePage.navigateTo();
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(jSessionId);
        driver.navigate().refresh();
    }

    @Test
    public void userCanStayLoggedInWithRememberedCookie() {
        String actual = homePage.getNavbar().getUserName();
        Assert.assertEquals(actual, "username");
    }



}
