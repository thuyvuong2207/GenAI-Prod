package testcases.feature_Authen_Author;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.HomePage;
import actions.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Register extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass() {
        driver = getBrowserDriver("chrome");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        createReport();
    }

    @Test
    public void Register_Success() {
        // Step: Onclick button Register on Login page
        // Verify field displayed for Email input
        // Input Email and go to next page
        // Open yopmail on new tab and get code
        // Go tab to prev tab and input code, password
        // Verify login with successfully registered account

        loginPage.inputLoginEmail("thuyvm+error@taureau.ai");
        Assert.assertTrue(loginPage.isLoginButtonVisible());
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isEmailAlertMsgDisplayed());
        Assert.assertEquals(loginPage.getEmailAlertMsgText());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        extent.flush();
    }
}
