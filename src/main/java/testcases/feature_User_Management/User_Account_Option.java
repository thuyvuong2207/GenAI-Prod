package testcases.feature_User_Management;


import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class User_Account_Option extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserProfilePage userProfilePage;
    private UserPricingPage userPricingPage;

    private HomePage homePage;
    String expectedAccountName = "test_demo";
    String expectedEmail = "test_demo@yopmail.com";

    @Parameters("browser")
    @BeforeClass
    public void beforeClass() {
        driver = getBrowserDriver("chrome");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputLoginEmail("test_demo@yopmail.com");
        loginPage.clickLoginButton();
        loginPage.inputLoginPassword("Taureau2132@");
        homePage = loginPage.clickLoginButton();

        createReport();
    }


    @Test
    public void Verify_Modal_User_Account_Options_On_Dashboard() {
        // Step:
        // Login and go to Dashboard
        // Onclick User Account Avatar
        // Verify modal display, list of options and state
        // Verify info on modal - name, email

        homePage.clickUserAccountButton(driver);
        Assert.assertTrue(homePage.isUserAccountModalDisplayed());
        Assert.assertEquals(homePage.getSizeOfAccountOptionsList(),3);
        Assert.assertTrue(isUserPricingDisplayedOnModal());
        Assert.assertTrue(isUserSettingsDisplayedOnModal());
        Assert.assertTrue(isLogoutButtonDisplayedOnModal());

        Assert.assertTrue(isUserNameDisplayedOnModal());
        Assert.assertTrue(isUserEmailDisplayedOnModal());
        Assert.assertTrue(isUserAvatarDisplayedOnModal());

        String accountName = homePage.getUserAccountNameTextOnModal();
        Assert.assertEquals(accountName, expectedAccountName);

        String accountEmail = homePage.getUserAccountEmailTextOnModal();
        Assert.assertEquals(accountEmail, expectedEmail);

    }

    @Test
    public void Verify_Redirect() {
        // Step:
        // Onclick Settings > verify redirect
        // Onclick Pricing > verify redirect
        // Onclick Logout > verify redirect

        homePage.clickUserAccountButton(driver);
        userProfilePage = homePage.clickUserSettings();
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),expectedAccountName);
        Assert.assertEquals(userProfilePage.getUserAccountEmailText(),expectedEmail);

        homePage = userProfilePage.clickHomepageButton();
        homePage.clickUserAccountButton(driver);
        userPricingPage = homePage.clickUserPricingButton();
        Assert.assertEquals(userPricingPage.getUserPricingPageTitle(), "Plan & Pricing");

        homePage = userPricingPage.clickHomepageButton();
        loginPage = homePage.clickLogoutButton();
        Assert.assertEquals(loginPage.getLoginPageTitle(), "Login");
    }

    @AfterClass
    public void afterClass() {
        extent.flush();
        driver.quit();
    }

}
