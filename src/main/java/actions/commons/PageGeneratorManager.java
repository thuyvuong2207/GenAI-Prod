package actions.commons;

import actions.pageObjects.*;
import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
    public static AIFoundationPage getAIFoundationPage(WebDriver driver){
        return new AIFoundationPage(driver);
    }
    public static HomePage getHomePage (WebDriver driver){
        return new HomePage(driver);
    }
    public static KnowledgeBasePage getKnowledgeBasePage(WebDriver driver){
        return new KnowledgeBasePage();
    }
    public static LoginPage getLoginPage(WebDriver driver){
        return new LoginPage(driver);
    }
    public static ToolCollectionPage getToolCollectionPage(WebDriver driver){
        return new ToolCollectionPage();
    }
    public static ToolCategoryPage getToolCategoryPage(WebDriver driver){
        return new ToolCategoryPage(driver);
    }
    public static ToolMarketplacePage getToolMarketplacePage(WebDriver driver){
        return new ToolMarketplacePage();
    }
    public static WorkerPage getWorkerPage(WebDriver driver){
        return new WorkerPage();
    }
    public static WorkflowMarketplacePage getWorkflowMarketplacePage(WebDriver driver){
        return new WorkflowMarketplacePage(driver);
    }
    public static DashboardPage getDashboardPage(WebDriver driver){
        return new DashboardPage();
    }
    public static MyWorkflowPage getMyWorkflowPage(WebDriver driver){
        return new MyWorkflowPage(driver);
    }

    public static UserPricingPage getUserPricingPage(WebDriver driver) {
        return new UserPricingPage(driver);
    }
    public static UserProfilePage getUserProfilePage(WebDriver driver) {
        return new UserProfilePage(driver);
    }
    public static ExtensionOptionPage getExtensionOptionPage(WebDriver driver) {
        return new ExtensionOptionPage(driver);
    }

}