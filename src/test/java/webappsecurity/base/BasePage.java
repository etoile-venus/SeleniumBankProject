package webappsecurity.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import webappsecurity.components.AccountBankMenu;
import webappsecurity.components.Navbar;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected String url;
    protected Navbar navbar;
    protected AccountBankMenu bankMenu;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
        url = "http://zero.webappsecurity.com";
        navbar = new Navbar(driver, wait, actions);
        bankMenu = null;
    }

    public void navigateTo() {
        driver.navigate().to(url);
    }
    public String getCompleteUrl() {
        return url;
    }
    public Navbar getNavbar() {
        return navbar;
    }
    public AccountBankMenu getBankMenu() {
        return bankMenu;
    }
}
