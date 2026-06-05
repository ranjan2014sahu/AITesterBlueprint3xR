package com.sf.tests;

import com.sf.base.BaseTest;
import com.sf.pages.LoginPage;
import com.sf.utils.ConfigReader;
import org.testng.annotations.Test;
import org.testng.Assert;

public class InvalidLoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify login fails with invalid username")
    public void verifyInvalidUsername() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            String invalidUsername = ConfigReader.getInvalidUsername();
            String validPassword = ConfigReader.getValidPassword();

            loginPage.login(invalidUsername, validPassword);

            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            Assert.assertTrue(isErrorDisplayed,
                    "Error message not displayed for invalid username");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify login fails with invalid password")
    public void verifyInvalidPassword() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            String validUsername = ConfigReader.getValidUsername();
            String invalidPassword = ConfigReader.getInvalidPassword();

            loginPage.login(validUsername, invalidPassword);

            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            Assert.assertTrue(isErrorDisplayed,
                    "Error message not displayed for invalid password");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Verify login fails with both invalid username and password")
    public void verifyBothCredentialsInvalid() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            String invalidUsername = ConfigReader.getInvalidUsername();
            String invalidPassword = ConfigReader.getInvalidPassword();

            loginPage.login(invalidUsername, invalidPassword);

            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            Assert.assertTrue(isErrorDisplayed,
                    "Error message not displayed for both invalid credentials");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 4, description = "Verify login fails with empty username")
    public void verifyEmptyUsername() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            String validPassword = ConfigReader.getValidPassword();

            loginPage.login("", validPassword);

            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            Assert.assertTrue(isErrorDisplayed,
                    "Error message not displayed for empty username");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 5, description = "Verify login fails with empty password")
    public void verifyEmptyPassword() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            String validUsername = ConfigReader.getValidUsername();

            loginPage.login(validUsername, "");

            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            Assert.assertTrue(isErrorDisplayed,
                    "Error message not displayed for empty password");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 6, description = "Verify login fails with both empty fields")
    public void verifyBothFieldsEmpty() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            loginPage.login("", "");

            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            Assert.assertTrue(isErrorDisplayed,
                    "Error message not displayed for both empty fields");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }
}
