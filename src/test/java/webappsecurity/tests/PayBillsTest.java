package webappsecurity.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import webappsecurity.base.BaseTest;
import webappsecurity.pages.LoginPage;
import webappsecurity.pages.PayBillsPage;
import webappsecurity.pages.TransferFundsPage;

public class PayBillsTest extends BaseTest {
    private PayBillsPage payBillsPage;

    @BeforeClass
    public void cookiesSetup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login("username", "password");

        payBillsPage = new PayBillsPage(driver);
        payBillsPage.navigateTo();
        jSessionId = driver.manage().getCookieNamed("JSESSIONID");
    }

    @BeforeMethod
    public void methodSetup() {
        payBillsPage.navigateTo();
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(jSessionId);
        driver.navigate().refresh();
    }

    @DataProvider(name = "payeeData")
    public Object[][] getPayeeData() {
        return new Object[][] {
                {"TestName", "TestAddress", "Loan", "TestDetails"}
        };
    }
    @Test(dataProvider = "payeeData")
    public void userAddsANewPayee(String name, String address, String account, String details) {
        payBillsPage.openAddNewPayeeTab();
        payBillsPage.addNewPayee(name, address, account, details);
        Assert.assertEquals(
                payBillsPage.getAlertMessageText(),
                "The new payee " + name + " was successfully created.",
                "Alert message text does not match!"
        );

        boolean payeeExists = payBillsPage.isPayeePresentForAccountSelect(account, name);
        Assert.assertTrue(payeeExists, "Payee '" + name + "' was not found in account '" + account + "'");
    }
}
