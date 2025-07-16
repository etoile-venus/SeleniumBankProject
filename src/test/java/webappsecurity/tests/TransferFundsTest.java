package webappsecurity.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webappsecurity.base.BaseTest;
import webappsecurity.pages.HomePage;
import webappsecurity.pages.LoginPage;
import webappsecurity.pages.TransferFundsPage;

public class TransferFundsTest extends BaseTest {
    private TransferFundsPage transferFundsPage;

    @BeforeClass
    public void cookiesSetup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login("username", "password");
        transferFundsPage = new TransferFundsPage(driver);
        transferFundsPage.navigateTo();
        jSessionId = driver.manage().getCookieNamed("JSESSIONID");
    }

    @BeforeMethod
    public void methodSetup() {
        transferFundsPage.navigateTo();
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(jSessionId);
        driver.navigate().refresh();
    }

    @Test
    public void transferFromSavingToLoanAccount() {

        transferFundsPage.fillOutForm("1", "4", "780", "Loan payment");
        transferFundsPage.clickContinueButton();
        transferFundsPage.clickSubmitButton();
        System.out.println(transferFundsPage.getMessage().getText());
    }
}
