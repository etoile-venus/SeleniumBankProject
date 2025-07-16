package webappsecurity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webappsecurity.base.BasePage;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver, String query) {
        super(driver);
        super.url = super.url + "/search.html?searchTerm=" + query;
    }

    public void waitForPageToLoad() {
        super.waitForPageToLoad(searchInput);
    }

    private final By searchInput = By.id("searchTerm");
    private final By container = By.className("top_offset");
    private final By result = By.cssSelector(".top_offset ul li a");

    public WebElement getContainer() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(container));
    }
    public List<WebElement> getResults() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(result));
    }

    public String getResultName(WebElement element) {
        return element.getText();
    }
    public String getResultHref(WebElement element) {
        return element.getAttribute("href");
    }


}
