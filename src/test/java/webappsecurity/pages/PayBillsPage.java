package webappsecurity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import webappsecurity.base.BasePage;
import webappsecurity.components.AccountBankMenu;

import java.util.List;

public class PayBillsPage extends BasePage {

    public PayBillsPage(WebDriver driver) {
        super(driver);
        super.url = super.url + "/bank/pay-bills.html";
        super.bankMenu = new AccountBankMenu(driver);
    }

    public void waitForPageToLoad() {
        super.waitForPageToLoad(paySavedPayeeTab);
    }

    private final By paySavedPayeeTab = By.cssSelector("a[href='#ui-tabs-1']");
    private final By addNewPayeeTab = By.cssSelector("a[href='#ui-tabs-2']");
    private final By purchaseForeignCurrencyTab = By.cssSelector("a[href='#ui-tabs-3']");
    private final By alertMessage = By.id("alert_content");

    public WebElement getPaySavedPayeeTab() {
        return wait.until(ExpectedConditions.elementToBeClickable(paySavedPayeeTab));
    }
    public WebElement getAddNewPayeeTab() {
        return wait.until(ExpectedConditions.elementToBeClickable(addNewPayeeTab));
    }
    public WebElement getPurchaseForeignCurrencyTab() {
        return wait.until(ExpectedConditions.elementToBeClickable(purchaseForeignCurrencyTab));
    }
    public WebElement getAlertMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
    }

    public void openPaySavedPayeeTab() {
        getPaySavedPayeeTab().click();
    }
    public void openAddNewPayeeTab() {
        getAddNewPayeeTab().click();
    }
    public void openPurchaseForeignCurrencyTab() {
        getPurchaseForeignCurrencyTab().click();
    }
    public String getAlertMessageText() {
        return getAlertMessage().getText();
    }

    private final By payeeNameInput = By.id("np_new_payee_name");
    private final By payeeAddressTextarea = By.id("np_new_payee_address");
    private final By payeeAccountInput = By.id("np_new_payee_account");
    private final By payeeDetailsInput = By.id("np_new_payee_details");
    private final By addNewPayeeButton = By.id("add_new_payee");

    public void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void enterPayeeName(String text) {
        enterText(payeeNameInput, text);
    }
    public void enterPayeeAddress(String text) {
        enterText(payeeAddressTextarea, text);
    }
    public void enterPayeeAccount(String text) {
        enterText(payeeAccountInput, text);
    }
    public void enterPayeeDetails(String text) {
        enterText(payeeDetailsInput, text);
    }
    public void clickAddNewPayeeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewPayeeButton)).click();
    }
    public void fillNewPayeeForm(String name, String address, String account, String details) {
        enterPayeeName(name);
        enterPayeeAddress(address);
        enterPayeeAccount(account);
        enterPayeeDetails(details);
    }
    public void addNewPayee(String name, String address, String account, String details) {
        fillNewPayeeForm(name, address, account, details);
        clickAddNewPayeeButton();
    }

    private final By payeeSelector = By.id("sp_payee");
    private final By accountSelector = By.id("sp_account");
    public WebElement getAccountSelectElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountSelector));
    }
    public WebElement getPayeeSelectElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payeeSelector));
    }
    public Select getAccountDropdown() {
        return new Select(getAccountSelectElement());
    }
    public Select getPayeeDropdown() {
        return new Select(getPayeeSelectElement());
    }
    public void selectAccountByName(String accountName) {
        Select accountDropdown = getAccountDropdown();
        accountDropdown.selectByVisibleText(accountName);
    }
    public void selectPayeeByName(String payeeName) {
        Select payeeDropdown = getPayeeDropdown();
        payeeDropdown.selectByVisibleText(payeeName);
    }
    public boolean isPayeePresentForAccountSelect(String accountName, String payeeName) {
        getAccountDropdown().selectByVisibleText(accountName);

        List<WebElement> options = getPayeeDropdown().getOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(payeeName)) {
                return true;
            }
        }
        return false;
    }
}
