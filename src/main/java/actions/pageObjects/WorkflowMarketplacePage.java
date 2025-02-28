package actions.pageObjects;

import actions.commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkflowMarketplacePage extends BasePageFactory {
    // Main tab
    @FindBy(xpath = "//span[text()='Business Category']")
    private WebElement businessCategoryButton;
    @FindBy(xpath = "//body/div/div/div/div/div/div/div/div[2]/div[1]")
    private WebElement bankingFinanceCategory;
    @FindBy(xpath = "//body/div/div/div/div/div/div/div/div[2]/div[2]")
    private WebElement travelHospitalityCategory;
    @FindBy(xpath = "//body/div/div/div/div/div/div/div/div[2]/div[3]")
    private WebElement educationCategory;
    @FindBy(xpath = "//body/div/div/div/div/div/div/div/div[2]/div[4]")
    private WebElement ecommerceCategory;
    @FindBy(xpath = "//body/div/div/div/div/div/div/div/div[2]/div[5]")
    private WebElement healthcareCategory;
    @FindBy(css = "div#search-bar>input")
    private WebElement searchBar;


    // Banking Finance Tab
    @FindBy(xpath = "//div[@class='mt-[20px] flex-1 overflow-hidden']//span[text()='BANKING AND FINANCE']")
    private WebElement bankingFinanceSectionOnMain;
    @FindBy(xpath = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[1]//span[text()='Load more']")
    private WebElement bankingFinanceLoadMoreButton;
    String bankingFinanceListOnMainLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[1]/div[2]/div[1]/div";
    String bankingFinanceListOnTabLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div";

    // Travel Hospitality Tab
    @FindBy(xpath = "//div[@class='mt-[20px] flex-1 overflow-hidden']//span[text()='TRAVEL AND HOSPITALITY']")
    private WebElement travelHospitalitySectionOnMain;
    @FindBy(xpath = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[2]//span[text()='Load more']")
    private WebElement travelHospitalityLoadMoreButton;
    String travelHospitalityListOnMainLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[1]/div[2]/div[2]/div";
    String travelHospitalityListOnTabLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div[1]/div/div";


    // Education Tab
    @FindBy(xpath = "//div[@class='mt-[20px] flex-1 overflow-hidden']//span[text()='EDUCATION']")
    private WebElement educationSectionOnMain;
    @FindBy(xpath = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[3]//span[text()='Load more']")
    private WebElement educationLoadMoreButton;
    String educationListOnMainLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[1]/div[2]/div[3]/div";
    String educationListOnTabLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div[1]/div/div";


    // Ecommerce Tab
    @FindBy(xpath = "//div[@class='mt-[20px] flex-1 overflow-hidden']//span[text()='E-COMMERCE']")
    private WebElement ecommerceSectionOnMain;
    @FindBy(xpath = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[4]//span[text()='Load more']")
    private WebElement ecommerceLoadMoreButton;
    String ecommerceListOnMainLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[1]/div[2]/div[4]/div";
    String ecommerceListOnTabLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div";

    // Healthcare Tab
    @FindBy(xpath = "//div[@class='mt-[20px] flex-1 overflow-hidden']//span[text()='HEALTHCARE']")
    private WebElement healthcareSectionOnMain;
    @FindBy(xpath = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[5]//span[text()='Load more']")
    private WebElement healthcareLoadMoreButton;
    String healthcareListOnMainLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div[1]/div[2]/div[5]/div";
    String healthcareListOnTabLocator = "//body/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div";

    WebDriver driver;

    public WorkflowMarketplacePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public boolean isBusinessCategoryDisplayed() {
        return businessCategoryButton.isDisplayed();
    }
    public boolean isBankingFinanceCategoryDisplayed() {
        return bankingFinanceCategory.isDisplayed();
    }
    public boolean isTravelHospitalityCategoryDisplayed() {
        return travelHospitalityCategory.isDisplayed();
    }
    public boolean isEducationCategoryDisplayed() {
        return educationCategory.isDisplayed();
    }
    public boolean isEcommerceCategoryDisplayed() {
        return ecommerceCategory.isDisplayed();
    }
    public boolean isHealthcareCategoryDisplayed() {
        return healthcareCategory.isDisplayed();
    }
    public void clickBankingFinanceCategory() {
        waitForElementClickable(driver, bankingFinanceCategory);
        bankingFinanceCategory.click();
    }
    public void clickTravelHospitalityCategory() {
        waitForElementClickable(driver, travelHospitalityCategory);
        travelHospitalityCategory.click();
    }
    public void clickEducationCategory() {
        waitForElementClickable(driver, educationCategory);
        educationCategory.click();
    }
    public void clickEcommerceCategory() {
        waitForElementClickable(driver, ecommerceCategory);
        ecommerceCategory.click();
    }
    public void clickHealthcareCategory() {
        waitForElementClickable(driver, healthcareCategory);
        healthcareCategory.click();
    }
    public void countWfInBankingTab(){
        waitForElementVisible(driver, bankingFinanceCategory);
        bankingFinanceCategory.click();
        waitForListElementVisible(driver,getListWebElement(driver, bankingFinanceListOnTabLocator));
        getListElementSize(driver,bankingFinanceListOnTabLocator);
    }
    public void countWfInTravelTab(){
        waitForElementVisible(driver, travelHospitalityCategory);
        travelHospitalityCategory.click();
        waitForListElementVisible(driver,getListWebElement(driver, travelHospitalityListOnTabLocator));
        getListElementSize(driver,travelHospitalityListOnTabLocator);
    }
    public void search(String searchValue){
        waitForElementVisible(driver, searchBar);
        searchBar.sendKeys(searchValue);
    }

}
