package actions.pageObjects;

import actions.commons.BasePageFactory;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyWorkflowPage extends BasePageFactory {
    @FindBy(xpath = "//img[contains(@src,'genai-logo')]")
    private WebElement buttonHome;
    @FindBy(xpath = "//div[text()='Add new workflow']/parent::div//parent::button")
    private WebElement addNewWorkflowButton;
    @FindBy(xpath = "//div[text()='Click here to create your own workflow']")
    private WebElement addNewWorkflowDescription;
    @FindBy(xpath = "//div[@class='rc-tooltip-inner']")
    private WebElement tooltipPublishButton;
    @FindBy(xpath = "//div[text()='Please confirm to grant the purchaser authorization to utilize these private workers']/parent::div")
    private WebElement modalConfirmWorker;
    @FindBy(xpath = "//span[text()='Confirm']/ancestor::button")
    private WebElement buttonConfirmWorker;
    @FindBy(xpath = "//div[text()='Publish your tool to proceed']/parent::div/parent::div/parent::div/parent::div/parent::div")
    private WebElement warningDialogToolUnpublished;
    @FindBy(xpath = "//div[text()='Publish your tool to proceed']/parent::div/parent::div/following-sibling::button")
    private WebElement buttonOKDialogToolUnpublished;
    @FindBy(xpath = "//span[text()='Choose the appropriate business category to publish your workflow']/parent::div/parent::div")
    private WebElement modalSelectCategory;
    @FindBy(css = "div#search-bar>input")
    private WebElement searchBar;


    String myWorkflowListLocator = "//body/div[1]/div[2]/div[2]/div[2]/div/div[2]/div/div/div";
    String publishButtonLocator = "//body/div[1]/div[2]/div[2]/div[2]/div/div[2]/div[1]/div/div";
    WebDriver driver;

    public MyWorkflowPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public boolean isAddNewButtonDisplayed() {
        return addNewWorkflowButton.isDisplayed();
    }
    public void clickAddNewButton() {
        waitForElementClickable(driver, addNewWorkflowButton);
        addNewWorkflowButton.click();
    }
//    public int countOfWfList1stPage() {
//        return getListElementSize(driver, myWorkflowListLocator);
//    }
    public void hoverOnWorkflowCard(Integer wfIndex){
        WebElement workflowCard = getWebElement(driver, myWorkflowListLocator + "[" + wfIndex + "]");
        hoverMouseToElement(driver, workflowCard);
    }

    public boolean isPublishButtonDisplayed(Integer wfIndex) {
        WebElement publishButton = getWebElement(driver, publishButtonLocator + "[" + wfIndex + "]/div[2]");
        waitForElementVisible(driver,publishButton);
        hoverMouseToElement(driver, publishButton);
        return publishButton.isDisplayed();
    }
    public String getTooltipTextPublishButton(Integer wfIndex) {
        WebElement publishButton = getWebElement(driver, publishButtonLocator + "[" + wfIndex + "]/div[2]");
        System.out.println(publishButtonLocator + "[" + (wfIndex + 1) + "]");
        waitForElementVisible(driver,publishButton);
        hoverMouseToElement(driver, publishButton);
        waitForElementVisible(driver, tooltipPublishButton);
        return tooltipPublishButton.getText();
    }
    public String getWorkflowName(Integer wfIndex) {
        WebElement workflowName = getWebElement(driver, myWorkflowListLocator + "[" + wfIndex + "]" + "/div[2]/div[1]");
        waitForElementVisible(driver, workflowName);
        return workflowName.getText();
    }
    public void clickPublishButton(Integer wfIndex){
        WebElement publishButton = getWebElement(driver, publishButtonLocator + "[" + wfIndex + "]/div[2]");
        hoverMouseToElement(driver, publishButton);
        waitForElementClickable(driver,publishButton);
        publishButton.click();
    }
    public boolean isModalConfirmWorkerDisplayed(Integer wfIndex) {
        waitForElementVisible(driver, modalConfirmWorker);
        return modalConfirmWorker.isDisplayed();
    }
    public void clickConfirmWorkerButton() {
        waitForElementClickable(driver, buttonConfirmWorker);
        buttonConfirmWorker.click();
        sleepInSeconds(2);
    }
    public boolean isWarningDialogToolUnpublishedDisplayed() {
        waitForElementVisible(driver, warningDialogToolUnpublished);
        return warningDialogToolUnpublished.isDisplayed();
    }
    public void clickOKDialogToolUnpublished() {
        waitForElementVisible(driver, warningDialogToolUnpublished);
        waitForElementClickable(driver, buttonOKDialogToolUnpublished);
        buttonOKDialogToolUnpublished.click();
        sleepInSeconds(2);
    }
    public boolean isModalSelectCategoryDisplayed(){
        waitForElementVisible(driver, modalSelectCategory);
        return modalSelectCategory.isDisplayed();
    }
    public HomePage clickHome(){
        waitForElementClickable(driver, buttonHome);
        buttonHome.click();
        return PageGeneratorManager.getHomePage(driver);
    }
    public void search(String searchValue){
        waitForElementVisible(driver, searchBar);
        searchBar.sendKeys(searchValue);
    }

}
