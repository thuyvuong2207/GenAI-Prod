package testcases.feature_User_Management;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.HomePage;
import actions.pageObjects.LoginPage;
import actions.pageObjects.UserProfilePage;
import actions.reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class View_Update_User_Profile extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserProfilePage userProfilePage;
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
    public void Change_User_Name_And_Save() {
        // Step:
        // Login. Onclick User avatar and onclick Settings
        // On Profile setting page, change username and save
        // Verify info on page and on modal user account
        // Reset name back to default name

        ExtentTestManager.startTest(log.getName(), "TC_Change_User_Name_And_Save");
        ExtentTestManager.getTest().log(Status.INFO, "Starting TC");
        homePage.clickUserAccountButton(driver);
        userProfilePage = homePage.clickUserSettings();

        ExtentTestManager.getTest().log(Status.INFO, "Verify name and email");
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),expectedAccountName);
        Assert.assertEquals(userProfilePage.getUserAccountEmailText(),expectedEmail);

        ExtentTestManager.getTest().log(Status.INFO, "Verify button state");
        Assert.assertFalse(userProfilePage.isButtonSaveEnabled());
        Assert.assertTrue(userProfilePage.isButtonBackEnabled());
        Assert.assertFalse(userProfilePage.isUserEmailEnabled());

        ExtentTestManager.getTest().log(Status.INFO, "Change name, save and verify");
        userProfilePage.setNewUserAccountName(driver,"random 123!@#$%^&*()-+");
        userProfilePage.clickButtonSave();
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),"random 123!@#$%^&*()-+");
        userProfilePage.clickUserAccountButton(driver);
        Assert.assertEquals(userProfilePage.getUserAccountNameTextOnModal(),"random 123!@#$%^&*()-+");
        Assert.assertEquals(userProfilePage.getUserAccountEmailTextOnModal(),expectedEmail);

        ExtentTestManager.getTest().log(Status.INFO, "Change back to default and save");
        userProfilePage.setNewUserAccountName(driver,expectedAccountName);
        userProfilePage.clickButtonSave();
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),expectedAccountName);

        ExtentTestManager.getTest().log(Status.INFO, "Ending TC");

    }

    @Test
    public void Change_User_Name_Not_Save_Then_Back() {
        // Step:
        // Login. Onclick User avatar and onclick Settings
        // On Profile setting page, change username and not save
        // Onclick Back button
        // Open Profile setting page again. Verify info not change.

        ExtentTestManager.startTest(log.getName(), "TC_Change_User_Name_And_Not_Save");
        ExtentTestManager.getTest().log(Status.INFO, "Starting TC");
        homePage.clickUserAccountButton(driver);
        userProfilePage = homePage.clickUserSettings();

        ExtentTestManager.getTest().log(Status.INFO, "Verify name and email");
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),expectedAccountName);
        Assert.assertEquals(userProfilePage.getUserAccountEmailText(),expectedEmail);

        ExtentTestManager.getTest().log(Status.INFO, "Verify button state");
        Assert.assertFalse(userProfilePage.isButtonSaveEnabled());
        Assert.assertTrue(userProfilePage.isButtonBackEnabled());
        Assert.assertFalse(userProfilePage.isUserEmailEnabled());

        ExtentTestManager.getTest().log(Status.INFO, "Change name and onclick Back button");
        userProfilePage.setNewUserAccountName(driver,"random 123!@#$%^&*()-+");
        homePage = userProfilePage.clickButtonBack();
        homePage.clickUserAccountButton(driver);
        userProfilePage = homePage.clickUserSettings();

        ExtentTestManager.getTest().log(Status.INFO, "Verify account name and email after Back");
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),expectedAccountName);
        userProfilePage.clickUserAccountButton(driver);
        Assert.assertEquals(userProfilePage.getUserAccountNameTextOnModal(),expectedAccountName);
        Assert.assertEquals(userProfilePage.getUserAccountEmailTextOnModal(),expectedEmail);

        ExtentTestManager.getTest().log(Status.INFO, "Change back to default and save");
        userProfilePage.setNewUserAccountName(driver,expectedAccountName);
        userProfilePage.clickButtonSave();
        Assert.assertEquals(userProfilePage.getUserAccountNameText(),expectedAccountName);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
        extent.flush();
    }
}
