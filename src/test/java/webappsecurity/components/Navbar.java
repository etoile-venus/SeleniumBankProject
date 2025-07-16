package webappsecurity.components;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webappsecurity.pages.HomePage;
import webappsecurity.pages.LoginPage;
import webappsecurity.pages.SearchPage;

import java.util.List;

public class Navbar {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public Navbar(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }

    private final By title = By.linkText("Zero Bank");
    private final By search = By.id("searchTerm");
    private final By signInButton = By.id("signin_button");
    private final By dropdown = By.className("dropdown");
    private final By userDropdown = By.cssSelector("i.icon-user");
    private final By settingsDropdown = By.cssSelector("i[class='icon-cog']");

    public WebElement getTitle() {
        return wait.until(ExpectedConditions.elementToBeClickable(title));
    }
    public WebElement getSearch() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(search));
    }
    public WebElement getSignInButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(signInButton));
    }
    public List<WebElement> getDropdowns() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdown));
    }
    public WebElement getUserDropdown() {
        for (WebElement dropdown : getDropdowns()) {
            if (!dropdown.findElements(userDropdown).isEmpty()) {
                return dropdown;
            }
        }
        return null;
    }
    public WebElement getSettingsDropdown() {
        for (WebElement dropdown : getDropdowns()) {
            if (!dropdown.findElements(settingsDropdown).isEmpty()) {
                return dropdown;
            }
        }
        return null;
    }

    public HomePage clickOnTitle() {
        getTitle().click();
        return new HomePage(driver);
    }
    public SearchPage enterSearchText(String text) {
        WebElement search = getSearch();
        search.clear();
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
        return new SearchPage(driver, text.replace(' ', '+'));
    }

    public LoginPage clickOnSignInButton() {
        getSignInButton().click();
        return new LoginPage(driver);
    }

    public String getUserName() {
        return getUserDropdown().getText().trim();
    }






}
