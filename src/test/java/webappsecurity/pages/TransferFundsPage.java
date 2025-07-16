package webappsecurity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import webappsecurity.base.BasePage;

public class TransferFundsPage extends BasePage {

    public TransferFundsPage(WebDriver driver) {
        super(driver);
        super.url = super.url + "/bank/transfer-funds.html";
    }

    private final By fromAccount = By.id("tf_fromAccountId");
    private final By toAccount = By.id("tf_toAccountId");
    private final By inputAmount = By.id("tf_amount");
    private final By inputDescription = By.id("tf_description");
    private final By continueButton = By.id("btn_submit");
    private final By submitButton = By.id("btn_submit");
    private final By message = By.cssSelector(".alert.alert-success");

    public WebElement getFromAccount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccount));
    }
    public WebElement getToAccount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(toAccount));
    }
    public WebElement getInputAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputAmount));
    }
    public WebElement getInputDescription() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputDescription));
    }
    public WebElement getContinueButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(continueButton));
    }
    public WebElement getSubmitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    }
    public WebElement getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message));
    }

    public void selectFromAccount(String value) {
        Select select = new Select(getFromAccount());
        select.selectByValue(value);
    }
    public void selectToAccount(String value) {
        Select select = new Select(getToAccount());
        select.selectByValue(value);
    }
    public void enterAmount(String amount) {
        WebElement input = getInputAmount();
        input.clear();
        input.sendKeys(amount);
    }
    public void enterDescription(String description) {
        WebElement input = getInputDescription();
        input.clear();
        input.sendKeys(description);
    }
    public void clickContinueButton() {
        getContinueButton().click();
    }
    public void clickSubmitButton() {
        getSubmitButton().click();
    }
    public String getMessageText() {
        return  getMessage().getText();
    }
    public void fillOutForm(String fromValue, String toValue, String amount, String description) {
        selectFromAccount(fromValue);
        selectToAccount(toValue);
        enterAmount(amount);
        enterDescription(description);
    }
}
