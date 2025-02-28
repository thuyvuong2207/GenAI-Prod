package actions.pageObjects;

import actions.commons.BasePageFactory;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePageFactory {
    @FindBy(xpath = "//button[contains(@id,'headlessui-popover-button')]")
    private WebElement userAccountButtonOnHeader;
    @FindBy(xpath = "//span[text()='Home']//parent::div")
    private WebElement sidebarHome;
    @FindBy(xpath = "//span[text()='Workflows']//parent::div")
    private WebElement sidebarWorkflows;
    @FindBy(xpath = "//span[text()='Workflows']//parent::div//following-sibling::div/div[1]")
    private WebElement sidebarWorkflowMarketplace;
    @FindBy(xpath = "//span[text()='Workflows']//parent::div//following-sibling::div/div[2]")
    private WebElement sidebarMyWorkflow;
    @FindBy(xpath = "//span[text()='Worker']//parent::div")
    private WebElement sidebarWorker;
    @FindBy(xpath = "//span[text()='Tools ']//parent::div")
    private WebElement sidebarTools;
    @FindBy(xpath = "//span[text()='Tools ']//parent::div//following-sibling::div/div[1]")
    private WebElement sidebarToolMarketplace;
    @FindBy(xpath = "//span[text()='Tools ']//parent::div//following-sibling::div/div[1]")
    private WebElement sidebarToolCollection;
    @FindBy(xpath = "//span[text()='Tools ']//parent::div//following-sibling::div/div[1]")
    private WebElement sidebarToolCategory;
    @FindBy(xpath = "//span[text()='Knowledge Base']//parent::div")
    private WebElement sidebarKnowledgeBase;
    @FindBy(xpath = "//span[text()='AI Foundation']//parent::div")
    private WebElement sidebarAIFoundation;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]")
    private WebElement userAccountModal;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]")
    private WebElement userAvatarOnAccountModal;
    @FindBy(xpath = "//div[contains(@class,'settings-header') and contains(@class,'cursor-pointer')]")
    private WebElement userSettingsOnAccountModal;
    @FindBy(xpath = "//div[contains(@class,'pricing-header') and contains(@class,'cursor-pointer')]")
    private WebElement userPricingOnAccountModal;
    @FindBy(xpath = "//div[contains(@class,'logout-header') and contains(@class,'cursor-pointer')]")
    private WebElement userLogoutButtonOnAccountModal;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]//following-sibling::div//span")
    private WebElement userNameOnAccountModal;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]//following-sibling::div//div")
    private WebElement userEmailOnAccountModal;
    @FindBy(xpath = "//span[text()='STUDIO']//following-sibling::div")
    private WebElement arrowStudioSidebar;

    WebDriver driver;
    String optionListLocator = "//div[contains(@class,'header') and contains(@class,'cursor-pointer')]";

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isTabDashboardVisible() {
        return sidebarHome.isDisplayed();
    }
    public boolean isTabWorkflowVisible() {
        return sidebarWorkflows.isDisplayed();
    }
    public boolean isTabWorkflowMarketplaceVisible() {
        return sidebarWorkflowMarketplace.isDisplayed();
    }
    public boolean isTabMyWorkflowVisible() {
        return sidebarMyWorkflow.isDisplayed();
    }
    public boolean isTabWorkerVisible() {
        return sidebarWorker.isDisplayed();
    }
    public boolean isTabToolsVisible() {
        return sidebarTools.isDisplayed();
    }
    public boolean isTabToolMarketplaceVisible() {
        return sidebarToolMarketplace.isDisplayed();
    }
    public boolean isTabToolCollectionVisible() {
        return sidebarToolCollection.isDisplayed();
    }
    public boolean isTabKnowledgeBaseVisible() {
        return sidebarKnowledgeBase.isDisplayed();
    }
    public boolean isTabAIFoundationVisible() {
        return sidebarAIFoundation.isDisplayed();
    }
    public boolean isUserAccountModalDisplayed() {
        return userAccountModal.isDisplayed();
    }

    public DashboardPage clickTabDashboard(){
        waitForElementClickable(driver, sidebarHome);
        sidebarHome.click();
        return PageGeneratorManager.getDashboardPage(driver);
    }
    public WorkflowMarketplacePage clickTabWorkflowMarketplace(){
        waitForElementClickable(driver, sidebarWorkflowMarketplace);
        sidebarWorkflowMarketplace.click();
        return PageGeneratorManager.getWorkflowMarketplacePage(driver);
    }
    public MyWorkflowPage clickTabMyWorkflow(){
        waitForElementClickable(driver, sidebarWorkflows);
        sidebarWorkflows.click();
        waitForElementClickable(driver, sidebarMyWorkflow);
        sidebarMyWorkflow.click();
        return PageGeneratorManager.getMyWorkflowPage(driver);
    }
    public WorkerPage clickTabWorkers(){
        waitForElementClickable(driver, sidebarWorker);
        sidebarWorker.click();
        return PageGeneratorManager.getWorkerPage(driver);
    }
    public ToolMarketplacePage clickTabToolMarketplace(){
        waitForElementClickable(driver, sidebarToolMarketplace);
        sidebarToolMarketplace.click();
        return PageGeneratorManager.getToolMarketplacePage(driver);
    }
    public ToolCollectionPage clickTabToolCollection(){
        waitForElementClickable(driver, sidebarToolCollection);
        sidebarToolCollection.click();
        return PageGeneratorManager.getToolCollectionPage(driver);
    }
    public ToolCategoryPage clickTabToolCategory(){
        waitForElementClickable(driver, sidebarToolCategory);
        sidebarToolCategory.click();
        return PageGeneratorManager.getToolCategoryPage(driver);
    }
    public KnowledgeBasePage clickTabKnowledgeBase(){
        waitForElementClickable(driver, sidebarKnowledgeBase);
        sidebarKnowledgeBase.click();
        return PageGeneratorManager.getKnowledgeBasePage(driver);
    }
    public AIFoundationPage clickTabAIFoundation(){
        waitForElementClickable(driver, sidebarAIFoundation);
        sidebarAIFoundation.click();
        sleepInSeconds(5);
        return PageGeneratorManager.getAIFoundationPage(driver);
    }
    public UserProfilePage clickSettingButton(){
        waitForElementClickable(driver, userSettingsOnAccountModal);
        userSettingsOnAccountModal.click();
        return PageGeneratorManager.getUserProfilePage(driver);
    }
    public void clickUserAccountButton(WebDriver driver){
        waitForElementClickable(driver,userAccountButtonOnHeader);
        setAttributeInDOM(driver,userAccountButtonOnHeader,"data-headlessui-state","open active");
    }
    public UserProfilePage clickUserSettings(){
        waitForElementClickable(driver, userAccountButtonOnHeader);
        userAccountButtonOnHeader.click();
        waitForElementClickable(driver, userSettingsOnAccountModal);
        userSettingsOnAccountModal.click();
        return PageGeneratorManager.getUserProfilePage(driver);
    }
    public UserPricingPage clickUserPricingButton() {
        waitForElementClickable(driver, userPricingOnAccountModal);
        userPricingOnAccountModal.click();
        return PageGeneratorManager.getUserPricingPage(driver);
    }


    public List<WebElement> getListOfAccountOptionsOnModal(){
        waitForElementVisible(driver, userAccountModal);
        return getListWebElement(driver,optionListLocator);
    }
    public int getSizeOfAccountOptionsList(){
        waitForElementVisible(driver, userAccountModal);
        return getListOfAccountOptionsOnModal().size();
    }
    public LoginPage clickLogoutButton(){
        waitForElementClickable(driver, userLogoutButtonOnAccountModal);
        userLogoutButtonOnAccountModal.click();
        return PageGeneratorManager.getLoginPage(driver);
    }
    public ExtensionOptionPage goToExtensionOptionPage(){
        driver.get("chrome-extension://knldjmfmopnpolahpmmgbagdohdnhkik/options/index.html");
        return PageGeneratorManager.getExtensionOptionPage(driver);
    }
    public void clickToArrowStudioSidebar() {
        waitForElementVisible(driver, arrowStudioSidebar);
        arrowStudioSidebar.click();
        sleepInSeconds(5);
    }
    public String getUserAccountNameTextOnModal() {
        return userNameOnAccountModal.getText();
    }
    public String getUserAccountEmailTextOnModal() {
        return userEmailOnAccountModal.getText();
    }



}
