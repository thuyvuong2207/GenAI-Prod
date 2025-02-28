package testcases.feature_Workflow_Management;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.HomePage;
import actions.pageObjects.LoginPage;
import actions.pageObjects.MyWorkflowPage;
import actions.pageObjects.WorkflowMarketplacePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Publish_Cancel_Publish_Wf extends BaseTest{
    private WebDriver driver;
    private LoginPage loginPage;
    private MyWorkflowPage myWorkflowPage;
    private WorkflowMarketplacePage workflowMarketplacePage;
    private HomePage homePage;
    String wfNameWithToolUnpublished = "Auto-Tool-Unpublished";
    String wfNameWithToolPublished = "Auto-Tool-Published";


    @Parameters("browser")
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        driver = getBrowserDriver("chrome");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.openPageURL(driver, "https://genai.taureau.ai/login");
        loginPage.inputLoginEmail("test_demo@yopmail.com");
        loginPage.clickLoginButton();
        loginPage.inputLoginPassword("Taureau2132@");
        homePage = loginPage.clickLoginButton();

        createReport();
    }

    @Test
    public void BR1_Publish_Workflow_Tool_Not_Published() {
        // Step:
        // Onclick "Publish" workflow. 1 of the tools is not published
        // Click Confirm on modal "Confirm worker"
        // Verify list of category on modal "Select Category"
        // Click Save
        // Observe warning dialog
        // Onclick OK on dialog
        // Verify on Marketplace


        myWorkflowPage = homePage.clickTabMyWorkflow();
        myWorkflowPage.search(wfNameWithToolUnpublished);
        Integer wfIndex = 1;
        String wfName = myWorkflowPage.getWorkflowName(wfIndex);

        myWorkflowPage.hoverOnWorkflowCard(wfIndex);
        Assert.assertTrue(myWorkflowPage.isPublishButtonDisplayed(wfIndex));
        myWorkflowPage.clickPublishButton(wfIndex);

        Assert.assertTrue(myWorkflowPage.isModalConfirmWorkerDisplayed(wfIndex));
        myWorkflowPage.clickConfirmWorkerButton();
        Assert.assertTrue(myWorkflowPage.isWarningDialogToolUnpublishedDisplayed());
        myWorkflowPage.clickOKDialogToolUnpublished();
        myWorkflowPage.getTooltipTextPublishButton(wfIndex);

        homePage = myWorkflowPage.clickHome();
        workflowMarketplacePage = homePage.clickTabWorkflowMarketplace();
        workflowMarketplacePage.search(wfName);
    }

    @Test
    public void BR1_Publish_Workflow_Tool_Published() {
        // Step:
        // Onclick "Publish" workflow. All tools published
        // Click Confirm on modal "Confirm worker"
        // Verify list of category on modal "Select Category"
        // Click Save
        // Verify on Marketplace


//        hoverOnWorkflowCard;
//        hoverOnPublishButton;
//        isPublishButtonDisplayed;
//        clickPublishButton;
//        isModalConfirmWorkerDisplayed;
//        clickConfirmWorkerButton;
//        isModalSelectCategoryDisplayed;
//        verifyListOfCategory;
//        selectCategoryFromDropDown;
//        clickSave;
//        verifyButtonOnCard;
//        verifyOnMarketplace;

    }

    @Test
    public void Abnormal_Already_Published_Workflow() {
        // Step:
        // Duplicate another tab My workflow.
        // On 1st tab: Onclick "Publish" workflow. All tools published
        // Click Confirm on modal "Confirm worker"
        // Verify list of category on modal "Select Category"
        // Click Save
        // Verify on Marketplace
        // On 2nd tab: Onclick "Publish" on same workflow. Observe alert
        // Verify on Marketplace

//        hoverOnWorkflowCard;
//        hoverOnPublishButton;
//        isPublishButtonDisplayed;
//        clickPublishButton;
//        isModalConfirmWorkerDisplayed;
//        clickConfirmWorkerButton;
//        isModalSelectCategoryDisplayed;
//        verifyListOfCategory;
//        selectCategoryFromDropDown;
//        clickSave;
//        isAlertAlreadyPublishDisplayed;
//        verifyButtonOnCard;
//        verifyOnMarketplace;
    }

    @AfterTest()
    public void afterTest() {
        extent.flush();
        driver.quit();
    }

}
