package webappsecurity.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import webappsecurity.base.BaseTest;
import webappsecurity.pages.AccountSummaryPage;
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

    @DataProvider(name = "formData")
    public Object[][] getFormData() {
        return new Object[][] {
                {"1", "5", "Paying off credit card"}
        };
    }

    @Test(dataProvider = "formData")
    public void transferFromSavingToLoanAccount(String fromAccount, String toAccount, String description) {
        String fromAccountName = transferFundsPage.getSelectedFromAccountName(fromAccount);
        String fromAccountBalance = transferFundsPage.getSelectedFromAccountBalance(fromAccount);
        String toAccountName = transferFundsPage.getSelectedToAccountName(toAccount);
        String toAccountBalance = transferFundsPage.getSelectedToAccountBalance(toAccount);


        String payment = String.valueOf(Math.abs(Double.parseDouble(toAccountBalance)));
        transferFundsPage.fillOutForm(fromAccount,toAccount,payment,description);
        transferFundsPage.clickContinueButton();
        transferFundsPage.clickSubmitButton();
        Assert.assertEquals(
                transferFundsPage.getMessageText(),
                "You successfully submitted your transaction.",
                "Transfer was not successful."
        );

        AccountSummaryPage summaryPage = transferFundsPage.getBankMenu().openAccountSummary();
        String fromAccountNewBalance = String.valueOf(Double.parseDouble(fromAccountBalance)-Double.parseDouble(payment));
        String toAccountNewBalance = String.valueOf(Double.parseDouble(toAccountBalance) + Double.parseDouble(payment));
        boolean resultCheckFromAccount = summaryPage.findAccountWithBalanceAmount(
                fromAccountName,
                fromAccountNewBalance
        );
        boolean resultCheckToAccount = summaryPage.findAccountWithBalanceAmount(
                toAccountName,
                toAccountNewBalance
        );
        Assert.assertTrue(
                resultCheckFromAccount,
                "Account balance for FROM account [" + fromAccountName + "] is not updated correctly in the summary page."
        );
        Assert.assertTrue(
                resultCheckToAccount,
                "Account balance for TO account [" + toAccountName + "] is not updated correctly in the summary page."
        );
    }
}
