package webappsecurity.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import webappsecurity.base.BaseTest;
import webappsecurity.pages.HomePage;
import webappsecurity.pages.LoginPage;
import webappsecurity.pages.SearchPage;

import java.util.List;

public class SearchTest extends BaseTest {
    private SearchPage searchPage;

    @BeforeMethod
    public void methodSetup() {
        searchPage = new SearchPage(driver, "");
        searchPage.navigateTo();
    }

    @DataProvider(name="queryData")
    public Object[][] queryParams() {
        return new Object[][] {
                {"Account"},
                {"Zero"}
        };
    }
    @Test(dataProvider = "queryData")
    public void resultsContainSearchParams(String query) {
        searchPage.getNavbar().enterSearchText(query);
        List<WebElement> results = searchPage.getResults();

        SoftAssert softAssert = new SoftAssert();
        for(WebElement result: results) {
            String link = searchPage.getResultHref(result);
            String name = searchPage.getResultName(result).toLowerCase();

            softAssert.assertTrue(name.contains(query.toLowerCase()), "Query isn't in the result");
            softAssert.assertTrue(link.startsWith("http://"), "Link does not start with '/'. Expected a root-relative path. Link: " + link);
            softAssert.assertTrue(link.endsWith(".html"), "Link does not end with '.html'. Link: " + link);
        }
        softAssert.assertAll("Search results aren't valid");
    }
}
