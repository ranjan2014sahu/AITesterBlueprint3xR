package com.sf.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.sf.utils.ConfigReader;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        try {
            initializeDriver();
            driver.get(ConfigReader.getUrl());
            wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage());
        }
    }

    private void initializeDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-blink-features=AutomationControlled");
            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
    }

    @AfterTest
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error during tearDown: " + e.getMessage());
        }
    }
}
