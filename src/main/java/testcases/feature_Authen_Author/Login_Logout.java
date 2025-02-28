package testcases.feature_Authen_Author;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.HomePage;
import actions.pageObjects.LoginPage;
import actions.pageObjects.UserProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login_Logout extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private UserProfilePage userProfilePage;

    String errorEmailMsg = "Email doesn’t exist";
    String validEmail = "test_demo@yopmail.com";
    String validPassword = "Taureau2132@";

    @Parameters("browser")
    @BeforeClass
    public void beforeClass() {
        driver = getBrowserDriver("chrome");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        createReport();
    }

    @Test
    public void BR1_Login_Local_Invalid_Email() {
        // Step: Input invalid email
        // Verify field password undisplayed
        // Verify button state and alert display upon onclick

        loginPage.inputLoginEmail("test_error@taureau.ai");
        Assert.assertTrue(loginPage.isLoginButtonVisible());
        loginPage.clickLoginButton();
//        Assert.assertFalse(loginPage.isPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isEmailAlertMsgDisplayed());
        Assert.assertEquals(loginPage.getEmailAlertMsgText(),errorEmailMsg);
    }

    @Test
    public void BR1_Login_Local_Invalid_Password() {
        // Step: Input valid email
        // Onclick button Continue
        // Input invalid password length (under 8 chars). Verify alert.
        // Input invalid password (valid length but incorrect). Verify alert.
        // Verify button state unclickable and alert display upon onclick.
        loginPage.clearLoginEmail();
        loginPage.inputLoginEmail(validEmail);
        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test
    public void BR1_Login_Logout_Local_Success() {
        // Step:
        // Login with valid credentials
        // Verify account info displayed
        loginPage.clearLoginEmail();
        loginPage.inputLoginEmail(validEmail);
        loginPage.clickLoginButton();
        loginPage.inputLoginPassword(validPassword);
        homePage = loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isTabHomeVisible());
        Assert.assertTrue(homePage.isTabKnowledgeBaseVisible());

        homePage.clickUserAccountButton(driver);
        userProfilePage = homePage.clickUserSettings();
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),validEmail.replace("@yopmail.com", ""));

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
        extent.flush();
    }
}
