package webappsecurity.components;

import org.openqa.selenium.WebDriver;
import webappsecurity.pages.AccountSummaryPage;
import webappsecurity.pages.TransferFundsPage;

public class AccountBankMenu {
    WebDriver driver;
    public AccountBankMenu(WebDriver driver) {
        this.driver = driver;
    }

    public AccountSummaryPage openAccountSummary() {
        AccountSummaryPage page = new AccountSummaryPage(driver);
        page.navigateTo();
        return page;
    }
    public TransferFundsPage openTransferFundsPage() {
        TransferFundsPage page = new TransferFundsPage(driver);
        page.navigateTo();
        return page;
    }
}
