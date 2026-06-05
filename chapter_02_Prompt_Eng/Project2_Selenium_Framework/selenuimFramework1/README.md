# Salesforce Login Automation Framework

## 📋 Overview

Enterprise-level Selenium automation framework for testing Salesforce login functionality. Built with Java, Maven, Selenium WebDriver 4, and TestNG following industry best practices and production-ready standards.

**Framework Type:** Page Object Model with PageFactory  
**Language:** Java 11  
**Build Tool:** Maven  
**Test Framework:** TestNG  
**Browser:** Chrome / Firefox  
**Test Scope:** Login page validation (valid & invalid credentials)  

---

## 🏗️ Project Structure

```
selenuimFramework1/
│
├── pom.xml                                    # Maven configuration with dependencies
├── testng.xml                                 # TestNG suite configuration
│
├── src/
│   ├── main/
│   │   ├── java/com/sf/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java             # WebDriver initialization, setup/teardown
│   │   │   ├── pages/
│   │   │   │   └── LoginPage.java            # Page Object Model with PageFactory
│   │   │   └── utils/
│   │   │       └── ConfigReader.java         # Configuration management
│   │   │
│   │   └── resources/
│   │       └── config.properties             # Test data & configuration
│   │
│   └── test/
│       └── java/com/sf/tests/
│           ├── ValidLoginTest.java           # Valid login test cases (3 tests)
│           └── InvalidLoginTest.java         # Invalid login test cases (6 tests)
│
└── README.md                                  # This file
```

---

## 🔧 Prerequisites

### System Requirements
- **Java:** JDK 11 or higher
- **Maven:** 3.6.0 or higher
- **Git:** (optional, for version control)
- **OS:** Windows, Mac, or Linux

### Software Installation

#### 1. Install Java JDK 11+
```bash
# Verify Java installation
java -version
```

#### 2. Install Maven
```bash
# Verify Maven installation
mvn -version
```

#### 3. Download WebDriver (Auto-managed by WebDriverManager)
- WebDriverManager automatically downloads compatible ChromeDriver
- No manual driver setup required

---

## 📦 Dependencies

All dependencies are managed in `pom.xml`:

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Selenium WebDriver | 4.15.0 | Browser automation |
| TestNG | 7.8.1 | Test execution framework |
| WebDriverManager | 5.6.3 | Automatic driver management |
| Log4j | 2.21.0 | Logging framework |

---

## ⚙️ Configuration

### Edit `config.properties`

**File Location:** `src/main/resources/config.properties`

```properties
# Browser Configuration
browser=chrome                                   # chrome or firefox

# Salesforce URL
salesforce.url=https://login.salesforce.com/?locale=in

# Test Credentials (IMPORTANT: Use your test account)
valid.username=testuser@salesforce.com          # Replace with your test username
valid.password=ValidPassword123!                # Replace with your test password
invalid.username=invalid@salesforce.com         # Non-existent user
invalid.password=WrongPassword123!              # Intentionally wrong password

# Wait Times (in seconds)
implicit.wait=10                                # Implicit wait for all elements
explicit.wait=15                                # Explicit wait for WebDriverWait
```

### Update Salesforce Credentials
**CRITICAL:** Replace test credentials with your actual Salesforce test account:
```bash
# config.properties
valid.username=your-actual-test@salesforce.com
valid.password=your-actual-test-password
```

---

## 🚀 Quick Start

### 1. Clone/Download Framework
```bash
cd c:\Users\Dell\AITesterBlueprint3x\chapter_02_Prompt_Eng\Project2_Selenium_Framework\selenuimFramework1
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run All Tests
```bash
mvn clean test
```

### 4. Run Specific Test Class
```bash
# Valid login tests only
mvn test -Dtest=ValidLoginTest

# Invalid login tests only
mvn test -Dtest=InvalidLoginTest
```

### 5. Run Specific Test Method
```bash
mvn test -Dtest=ValidLoginTest#verifyValidLogin
```

### 6. Run with TestNG XML (Full Suite)
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Test Cases

### Valid Login Tests (3 Test Cases)
Located in: `src/test/java/com/sf/tests/ValidLoginTest.java`

| Test # | Test Name | Description | Expected Result |
|--------|-----------|-------------|-----------------|
| 1 | `verifyLoginPageLoads` | Verify all login elements load | All elements visible |
| 2 | `verifyValidLogin` | Login with valid credentials | Redirected to home/dashboard |
| 3 | `verifyRememberMeCheckbox` | Login with Remember Me enabled | Successful login |

### Invalid Login Tests (6 Test Cases)
Located in: `src/test/java/com/sf/tests/InvalidLoginTest.java`

| Test # | Test Name | Description | Expected Result |
|--------|-----------|-------------|-----------------|
| 1 | `verifyInvalidUsername` | Invalid user + valid password | Error message displayed |
| 2 | `verifyInvalidPassword` | Valid user + invalid password | Error message displayed |
| 3 | `verifyBothCredentialsInvalid` | Both invalid | Error message displayed |
| 4 | `verifyEmptyUsername` | Empty username + valid password | Error message displayed |
| 5 | `verifyEmptyPassword` | Valid username + empty password | Error message displayed |
| 6 | `verifyBothFieldsEmpty` | Both empty | Error message displayed |

**Total Test Cases:** 9  
**Test Execution Time:** ~2-3 minutes (including login processing)

---

## 🎯 Framework Architecture

### BaseTest.java
**Responsibility:** WebDriver initialization and lifecycle management

**Features:**
- ✅ WebDriver setup in `@BeforeTest`
- ✅ Browser maximization and configuration
- ✅ Implicit & Explicit wait configuration
- ✅ Browser teardown in `@AfterTest`
- ✅ Exception handling for initialization failures
- ✅ Support for Chrome and Firefox browsers

**Code Flow:**
```
@BeforeTest → Initialize Driver → Navigate to URL → @Test Cases
→ @AfterTest → Quit Driver
```

---

### LoginPage.java
**Responsibility:** Page Object Model for Salesforce login page

**PageFactory Elements:**
```java
@FindBy(xpath = "//input[@id='username']")       // Username field
@FindBy(xpath = "//input[@id='password']")       // Password field
@FindBy(xpath = "//input[@id='Login']")          // Login button
@FindBy(xpath = "//input[@id='rememberUn']")     // Remember Me checkbox
@FindBy(xpath = "//div[@id='error'...]")         // Error message container
```

**Key Methods:**
- `verifyLoginPageLoaded()` - Validates page readiness
- `enterUsername(String)` - Input username with clear()
- `enterPassword(String)` - Input password with clear()
- `clickLoginButton()` - Click login with WebDriverWait
- `login(String, String)` - Complete login flow
- `getErrorMessage()` - Retrieve error text
- `isErrorMessageDisplayed()` - Boolean error check
- `isLoginSuccessful()` - Verify login success

**Exception Handling:** All methods use try-catch with descriptive error messages

---

### ConfigReader.java
**Responsibility:** Configuration property management

**Methods:**
- `getBrowser()` - Returns configured browser
- `getUrl()` - Returns Salesforce URL
- `getValidUsername()` - Returns valid test username
- `getValidPassword()` - Returns valid test password
- `getInvalidUsername()` - Returns invalid test username
- `getInvalidPassword()` - Returns invalid test password
- `getImplicitWait()` - Returns implicit wait duration
- `getExplicitWait()` - Returns explicit wait duration

---

## 🔍 Key Features

### ✨ Page Object Model (POM)
- Centralized element locators using `@FindBy` annotations
- Reusable action methods
- Easy maintenance and scalability
- Separation of concerns

### ✨ PageFactory Implementation
- Automatic WebElement initialization
- Constructor-based initialization
- Lazy element loading
- Reduced boilerplate code

### ✨ XPath Selectors Only
- No CSS selectors (framework requirement)
- No ID-based selectors
- No name-based selectors
- Dynamic XPath for flexible element identification

### ✨ Robust Exception Handling
- Try-catch blocks in all critical methods
- Descriptive error messages
- Graceful failure handling
- Stack trace preservation

### ✨ WebDriverWait Only (No Thread.sleep)
- Explicit waits with `WebDriverWait`
- Implicit waits configured globally
- Expected conditions for element readiness
- No artificial delays or Thread.sleep()

### ✨ TestNG Annotations
- `@BeforeTest` - Pre-test setup
- `@AfterTest` - Post-test cleanup
- `@Test` - Test method definition
- Priority-based execution order

### ✨ Configuration Management
- External property file (config.properties)
- Easy test data updates
- No hardcoding of values
- Environment-specific configuration

---

## 🧪 Running Tests

### CLI Execution

#### Run All Tests
```bash
mvn clean test
```

#### Run with TestNG Suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

#### Run Single Test Class
```bash
mvn test -Dtest=ValidLoginTest
mvn test -Dtest=InvalidLoginTest
```

#### Run Single Test Method
```bash
mvn test -Dtest=ValidLoginTest#verifyValidLogin
```

#### Run with Verbose Output
```bash
mvn test -X
```

---

## 📋 Execution Order (testng.xml)

TestNG Suite executes tests in this order:

**Group 1: Valid Login Tests**
1. `verifyLoginPageLoads()` (Priority: 1)
2. `verifyValidLogin()` (Priority: 2)
3. `verifyRememberMeCheckbox()` (Priority: 3)

**Group 2: Invalid Login Tests**
1. `verifyInvalidUsername()` (Priority: 1)
2. `verifyInvalidPassword()` (Priority: 2)
3. `verifyBothCredentialsInvalid()` (Priority: 3)
4. `verifyEmptyUsername()` (Priority: 4)
5. `verifyEmptyPassword()` (Priority: 5)
6. `verifyBothFieldsEmpty()` (Priority: 6)

---

## 🐛 Troubleshooting

### Issue: WebDriver Not Found
**Solution:**
```bash
# Clear Maven cache and reinstall
mvn clean install -U
```

### Issue: Salesforce XPath Not Working
**Solution:**
1. Inspect element in Chrome DevTools
2. Verify XPath selector in LoginPage.java
3. Update @FindBy annotations with correct XPath

### Issue: Tests Timing Out
**Solution:**
- Increase wait times in config.properties:
  ```properties
  implicit.wait=15
  explicit.wait=20
  ```
- Check internet connectivity to Salesforce

### Issue: Authentication Fails
**Solution:**
1. Verify credentials in config.properties
2. Ensure test account is active in Salesforce
3. Check for MFA/2FA requirements
4. Verify IP allowlist on Salesforce org

### Issue: Element Not Found
**Solution:**
1. Inspect element in browser
2. Update XPath in LoginPage.java
3. Verify implicit/explicit wait times
4. Check for dynamic element loading

---

## 📝 Best Practices Implemented

✅ **No Magic Numbers** - All values in config.properties  
✅ **No Thread.sleep()** - Only WebDriverWait used  
✅ **No Comments** - Self-documenting code  
✅ **Exception Handling** - Try-catch in critical paths  
✅ **Reusable Methods** - DRY principle followed  
✅ **Clear Naming** - Method names describe behavior  
✅ **Separation of Concerns** - POM, Base, Utils separation  
✅ **Configuration Externalization** - Properties file driven  
✅ **Consistent Structure** - Same pattern across all classes  
✅ **Enterprise Standards** - Production-ready code  

---

## 🔄 Extending the Framework

### Add New Test Cases
1. Create new test method in `ValidLoginTest.java` or `InvalidLoginTest.java`
2. Use `@Test` annotation with priority
3. Extend from `BaseTest` class
4. Use `LoginPage` methods for actions

**Example:**
```java
@Test(priority = 4, description = "Your test description")
public void verifyYourScenario() {
    try {
        LoginPage loginPage = new LoginPage(driver, wait);
        // Your test logic
        Assert.assertTrue(condition, "Assertion message");
    } catch (Exception e) {
        throw new RuntimeException("Test failed: " + e.getMessage());
    }
}
```

### Add New Page Objects
1. Create new class in `src/main/java/com/sf/pages/`
2. Extend PageFactory pattern from `LoginPage.java`
3. Use `@FindBy` with XPath selectors
4. Implement action methods with exception handling
5. Use WebDriverWait for element interactions

### Add New Utilities
1. Create new class in `src/main/java/com/sf/utils/`
2. Implement static methods for reusability
3. Add exception handling
4. Update tests to use new utilities

---

## 📞 Support & Maintenance

### Framework Maintenance
- Update Selenium WebDriver version in pom.xml
- Verify Salesforce UI changes don't break XPath selectors
- Update test credentials in config.properties periodically
- Review and update wait times based on environment

### Common Maintenance Tasks
```bash
# Update Maven dependencies
mvn dependency:tree

# Check for outdated dependencies
mvn versions:display-dependency-updates

# Run code analysis (if SonarQube configured)
mvn sonar:sonar

# Generate test reports
mvn surefire-report:report
```

---

## 🏆 Enterprise Standards

This framework follows industry best practices and enterprise standards:

| Standard | Implementation |
|----------|-----------------|
| **Code Organization** | Package-based hierarchy (com.sf.*) |
| **Naming Conventions** | CamelCase for classes, methods |
| **Error Handling** | Try-catch with descriptive messages |
| **Configuration Management** | External properties file |
| **Test Organization** | TestNG with priority-based execution |
| **Waits Strategy** | Implicit + Explicit waits |
| **Page Object Model** | PageFactory with @FindBy |
| **Reusability** | BaseTest inheritance, shared utilities |
| **Maintainability** | Modular, self-documenting code |
| **Scalability** | Easy to add new pages and tests |

---

## 📄 Files Summary

| File | Purpose | Type |
|------|---------|------|
| `pom.xml` | Maven configuration | Configuration |
| `testng.xml` | Test suite definition | Configuration |
| `BaseTest.java` | WebDriver setup | Base Class |
| `LoginPage.java` | Page Object Model | Page Object |
| `ConfigReader.java` | Configuration utility | Utility |
| `ValidLoginTest.java` | Valid login tests | Test Class |
| `InvalidLoginTest.java` | Invalid login tests | Test Class |
| `config.properties` | Test data & settings | Configuration |

---

## ✅ Checklist Before Running Tests

- [ ] Java 11+ installed and configured
- [ ] Maven 3.6+ installed and configured
- [ ] Salesforce test account created
- [ ] `config.properties` updated with valid credentials
- [ ] Internet connection available
- [ ] Salesforce accessible from your network
- [ ] Chrome or Firefox browser installed
- [ ] Maven dependencies downloaded (`mvn clean install`)

---

## 🎯 Next Steps

1. **Update Credentials:** Edit `config.properties` with your Salesforce test account
2. **Verify XPath:** Inspect Salesforce login page to confirm XPath selectors
3. **Run Tests:** Execute `mvn clean test`
4. **Review Results:** Check test output for pass/fail status
5. **Extend Framework:** Add more test cases and page objects as needed

---

## 📚 Additional Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Salesforce Testing Guide](https://developer.salesforce.com/docs)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [XPath Tutorial](https://www.w3schools.com/xml/xpath_intro.asp)

---

## 📄 License

Enterprise-level framework for educational and commercial use.

---

**Framework Version:** 1.0  
**Last Updated:** 2026-06-06  
**Maintenance:** Active  

For issues or enhancements, refer to the troubleshooting section or extend framework following the provided patterns.
