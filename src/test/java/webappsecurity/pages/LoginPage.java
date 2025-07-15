package webappsecurity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webappsecurity.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        super.url = super.url + "/login.html";
    }

    private final By inputUsername = By.id("user_login");
    private final By inputPassword = By.id("user_password");
    private final By signInButton = By.cssSelector("input[value='Sign in']");

    public WebElement getInputUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername));
    }
    public WebElement getInputPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword));
    }
    public WebElement getSignInButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(signInButton));
    }

    public void enterUsername(String username) {
        WebElement input = getInputUsername();
        input.clear();
        input.sendKeys(username);
    }
    public void enterPassword(String password) {
        WebElement input = getInputPassword();
        input.clear();
        input.sendKeys(password);
    }
    public void clickOnSignInButton() {
        getSignInButton().click();
    }

    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickOnSignInButton();
        return new HomePage(driver);
    }
}
