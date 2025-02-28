package testcases.feature_AI_Foundation_Management;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.*;
import actions.reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class View_List_AI_Foundation extends BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private AIFoundationPage aiFoundationPage;
    private HomePage homePage;


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
    public void Verify_Tab_AI_Foundation() {
        // Step:
        // Login and go to homepage
        // Verify display of tab AI Foundation
        // Click tab AI Foundation
        // Verify model list
        ExtentTestManager.startTest(log.getName(), "TC_Verify_Tab_AI_Foundation");
        ExtentTestManager.getTest().log(Status.INFO, "Starting TC");

        ExtentTestManager.getTest().log(Status.INFO, "Verify display of tab AI Foundation");
        homePage.clickToArrowStudioSidebar();
        Assert.assertTrue(homePage.isTabAIFoundationVisible());

        ExtentTestManager.getTest().log(Status.INFO, "Click tab AI Foundation");
        aiFoundationPage = homePage.clickTabAIFoundation();

        ExtentTestManager.getTest().log(Status.INFO, "Verify tab title");
        Assert.assertEquals(aiFoundationPage.getAIFoundationPageTitle(),"AI Foundation");

        ExtentTestManager.getTest().log(Status.INFO, "Verify tab sub-title");
        Assert.assertEquals(aiFoundationPage.getAIFoundationPageSubtitle(),"Empower worker to be more intelligent, responsive, and capable in a wide range of applications.");

        ExtentTestManager.getTest().log(Status.INFO, "Verify number of AI model listed");
        Assert.assertEquals(aiFoundationPage.getSizeOfModelList(),5);
        ExtentTestManager.getTest().log(Status.INFO, "Ending TC");

    }

    @AfterClass
    public void afterTest() {
        driver.quit();
        extent.flush();
    }
}
