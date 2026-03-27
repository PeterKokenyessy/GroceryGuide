package com.codecool.selenium.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import com.codecool.selenium.pages.SignInPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private SignInPage signInPage;
    private static final String BASE_URL = System.getProperty("e2e.baseUrl", "http://frontend-e2e:80");

    @BeforeEach
    public void setUp() throws Exception {

        // Start ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                options
        );

        driver.manage().window().maximize();

        // Initialize the page object
        signInPage = new SignInPage(driver);
    }

    @Test
    public void testLoginSuccess() {
        // 1. Open the login page
        signInPage.open(BASE_URL + "/signin");

        // 2. Perform login using test credentials
        signInPage.login("testuser", "testpass");

        // 3. Assert login success
        assertTrue(signInPage.isLoggedIn());
    }

    @AfterEach
    public void tearDown() {
        // Quit browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
