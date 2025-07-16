package webappsecurity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webappsecurity.base.BasePage;
import webappsecurity.components.AccountBankMenu;

import java.util.ArrayList;
import java.util.List;

public class AccountSummaryPage extends BasePage {

    public AccountSummaryPage(WebDriver driver) {
        super(driver);
        super.url = super.url + "/account-summary.html";
        super.bankMenu = new AccountBankMenu(driver);
    }

    private final By row = By.cssSelector("tbody tr");
    private final By accountType = By.cssSelector("td:nth-child(1) a");
    private final By creditCardNumber = By.cssSelector("td:nth-child(2)");
    private final By balanceAmount = By.cssSelector("td:nth-child(3)");


    public List<WebElement> getAllRows() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(row));
    }
    public List<WebElement> getAccountRowsByName(String accountTypeName) {
        List<WebElement> result = new ArrayList<>();
        List<WebElement> allRows = getAllRows();
        for (WebElement row : allRows) {
            WebElement accountElement = row.findElement(accountType);
            if (accountElement.getText().trim().equalsIgnoreCase(accountTypeName.trim())) {
                result.add(row);
            }
        }
        return result;
    }

    public String getBalanceFromRow(WebElement row) {
        return row.findElement(balanceAmount).getText().trim();
    }

    public String getCreditCardNumberFromRow(WebElement row) {
        return row.findElement(creditCardNumber).getText().trim();
    }

    public void clickAccountLinkInRow(WebElement row) {
        WebElement link = row.findElement(accountType);
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
    }

    public boolean findAccountWithBalanceAmount(String name, String balance) {
        List<WebElement> rowsByName = getAccountRowsByName(name);
        if(rowsByName.isEmpty()) return false;
        for(WebElement row: rowsByName) {
            String rowBalance = getBalanceFromRow(row);
            if (rowBalance.equals(balance))
                return true;
        }
        return false;
    }


}
