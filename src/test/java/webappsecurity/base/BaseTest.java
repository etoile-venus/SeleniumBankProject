package webappsecurity.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void classSetup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Ključni argumenti koji gase password menadžer u Chrome-u
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-autofill-keyboard-accessory-view-password-suggestions");
        options.addArguments("--disable-features=PasswordManager");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("disable-blink-features=CredentialManagement");

// Za bezbednosne sertifikate i lokalni razvoj
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-web-security");

// Isključivanje password managera preko profila (prefs)
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get((new BasePage(driver)).getCompleteUrl());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
