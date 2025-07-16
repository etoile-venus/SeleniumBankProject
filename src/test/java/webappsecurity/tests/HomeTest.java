package webappsecurity.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login("username", "password");

        homePage = new HomePage(driver);
        homePage.navigateTo();
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
        WebDriver tempDriver = new EdgeDriver();

        HomePage tempHomePage = new HomePage(tempDriver);
        tempHomePage.navigateTo();
        tempHomePage.waitForPageToLoad();

        tempDriver.manage().deleteAllCookies();
        tempDriver.manage().addCookie(jSessionId);
        tempDriver.navigate().refresh();

        String actual = tempHomePage.getNavbar().getUserName();
        Assert.assertEquals(actual, "username");

        tempDriver.quit();
    }



}
