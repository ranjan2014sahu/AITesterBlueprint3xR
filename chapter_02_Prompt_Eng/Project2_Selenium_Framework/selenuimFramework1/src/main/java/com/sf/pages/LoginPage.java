package com.sf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error' or @class='loginError' or contains(@class, 'error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//span[@class='uiButton' or contains(text(), 'Log In')]")
    private WebElement alternateLoginButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void verifyLoginPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            wait.until(ExpectedConditions.visibilityOf(loginButton));
        } catch (Exception e) {
            throw new RuntimeException("Login page elements not visible: " + e.getMessage());
        }
    }

    public void enterUsername(String username) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(usernameField));
            usernameField.clear();
            usernameField.sendKeys(username);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter username: " + e.getMessage());
        }
    }

    public void enterPassword(String password) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(passwordField));
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter password: " + e.getMessage());
        }
    }

    public void clickRememberMe() {
        try {
            if (!rememberMeCheckbox.isSelected()) {
                rememberMeCheckbox.click();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Remember Me: " + e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click login button: " + e.getMessage());
        }
    }

    public void login(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickLoginButton();
        } catch (Exception e) {
            throw new RuntimeException("Login operation failed: " + e.getMessage());
        }
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            throw new RuntimeException("Error message not found: " + e.getMessage());
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.urlContains("home"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
