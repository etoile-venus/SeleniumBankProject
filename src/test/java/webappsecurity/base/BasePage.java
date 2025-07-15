package webappsecurity.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected String url;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
        url = "http://zero.webappsecurity.com";
    }

    public void navigateTo() {
        driver.navigate().to(url);
    }
    public String getCompleteUrl() {
        return url;
    }

}
