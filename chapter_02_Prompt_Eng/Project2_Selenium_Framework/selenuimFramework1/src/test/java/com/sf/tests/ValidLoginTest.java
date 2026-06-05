package com.sf.tests;

import com.sf.base.BaseTest;
import com.sf.pages.LoginPage;
import com.sf.utils.ConfigReader;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ValidLoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify login page loads successfully")
    public void verifyLoginPageLoads() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();
            Assert.assertTrue(driver.getCurrentUrl().contains("login.salesforce.com"),
                    "Login page not loaded correctly");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify valid login with correct credentials")
    public void verifyValidLogin() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            String username = ConfigReader.getValidUsername();
            String password = ConfigReader.getValidPassword();

            loginPage.login(username, password);

            boolean isLoginSuccessful = loginPage.isLoginSuccessful();
            Assert.assertTrue(isLoginSuccessful,
                    "Login failed with valid credentials");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Verify remember me checkbox functionality")
    public void verifyRememberMeCheckbox() {
        try {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.verifyLoginPageLoaded();

            String username = ConfigReader.getValidUsername();
            String password = ConfigReader.getValidPassword();

            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickRememberMe();

            loginPage.clickLoginButton();

            boolean isLoginSuccessful = loginPage.isLoginSuccessful();
            Assert.assertTrue(isLoginSuccessful,
                    "Login failed with Remember Me checkbox enabled");
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage());
        }
    }
}
