package com.codecool.selenium.pages;

import com.codecool.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

    // Locators using data-testid
    private final By usernameInput = By.cssSelector("[data-testid='signin-username']");
    private final By passwordInput = By.cssSelector("[data-testid='signin-password']");
    private final By submitButton  = By.cssSelector("[data-testid='signin-submit']");
    private final By logoutButton = By.cssSelector("[data-testid='logout-button']");

    // Optional: email input if testing registration
    private final By emailInput = By.cssSelector("[data-testid='signin-email']");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    // Open the login page
    public void open(String url) {
        driver.get(url); // e.g., "http://localhost:3000/signin"
    }

    // Enter username
    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    // Enter password
    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    // Enter email (optional, only if testing register)
    public void enterEmail(String email) {
        type(emailInput, email);
    }

    // Click submit button
    public void clickSubmit() {
        click(submitButton);
    }

    // Combined login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }

    public boolean isLoggedIn() {
        return isElementVisible(logoutButton);
    }
}
