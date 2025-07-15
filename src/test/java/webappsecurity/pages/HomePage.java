package webappsecurity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webappsecurity.base.BasePage;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        super.url = super.url + "/index.html";
    }

    private final By signInButton = By.id("signin_button");
    private final By dropdowns = By.className("dropdown");
    private final By userMenu = By.cssSelector("i.icon-user");
    private final By settingsMenu = By.cssSelector("icon-cog");


    public WebElement getSignInButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(signInButton));
    }
    public WebElement getUserMenu() {
        List<WebElement> dropdownsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdowns));
        for (WebElement dropdown : dropdownsList) {
            if (!dropdown.findElements(userMenu).isEmpty()) {
                return dropdown;
            }
        }
        return null;
    }
    public WebElement getSettingsMenu() {
        List<WebElement> dropdownsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdowns));
        for (WebElement dropdown : dropdownsList) {
            if (!dropdown.findElements(settingsMenu).isEmpty()) {
                return dropdown;
            }
        }
        return null;
    }

    public LoginPage clickOnSignInButton() {
        getSignInButton().click();
        return new LoginPage(driver);
    }

    public String getUsername() {
        return getUserMenu().getText().trim();
    }
}
