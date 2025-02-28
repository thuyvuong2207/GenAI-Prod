package actions.pageObjects;

import actions.commons.BasePageFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ToolCategoryPage extends BasePageFactory {
    @FindBy(xpath = "//span[contains(@class,'text-heading') and text()='Tools']")
    private WebElement getPageTitleToolCategory1;
    @FindBy(xpath = "//span[contains(@class,'text-heading') and text()='Category']")
    private WebElement pageTitleToolCategory2;
    @FindBy(xpath = "//span[contains(@class,'text-heading') and text()='Category']//parent::div/following-sibling::span")
    private WebElement pageDescriptionToolCategory;

    @FindBy(xpath = "//div[text()='Add new category']//ancestor::button")
    private WebElement buttonAddNewToolCategory;
    @FindBy(xpath = "(//div[text()='Add new category']//ancestor::button//following-sibling::div)[2]")
    private WebElement firstCategoryCard;
    @FindBy(xpath = "(//div[text()='Add new category']//ancestor::button//following-sibling::div)[2]//div[contains(@class,'text-primary')]")
    private WebElement firstCategoryCardName;
    @FindBy(xpath = "(//div[text()='Add new category']//ancestor::button//following-sibling::div)[2]//div[contains(@class,'text-Secondary')]")
    private WebElement firstCategoryCardToolCount;
    @FindBy(xpath = "(//div[text()='Uncategorized']//parent::div)[1]")
    private WebElement UncategorizedCard;
    @FindBy(xpath = "//div[@class='icon-arrow-pagination single ']")
    private WebElement paginationSingleButton;
    @FindBy(xpath = "//div[@class='icon-arrow-pagination double ']")
    private WebElement paginationDoubleButton;
    @FindBy(xpath = "//span[@class='!text-Primary-Color text-subBody font-medium']")
    private WebElement paginationField;
    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/input")
    private WebElement paginationInputField;


     WebDriver driver;

    public ToolCategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isButtonAddNewVisible() {
        return buttonAddNewToolCategory.isDisplayed();
    }

    public boolean isUnCategorizedCardVisible(WebDriver driver, Integer i) {
        waitForElementClickable(driver, paginationInputField);
        paginationField.click();
        waitForElementVisible(driver, paginationInputField);
        for (i = 0; i < 100; i++) {
            paginationInputField.clear();
            paginationInputField.sendKeys(String.valueOf(i));
            paginationInputField.sendKeys(Keys.ENTER);
            List<WebElement> allCategoryCardName = getListWebElement(driver, "(//div[text()='Add new category']//ancestor::button//following-sibling::div//div[contains(@class,'text-primary')])");

            for (WebElement item : allCategoryCardName) {
                if (item.getText().trim().equals("Uncategorized")) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public Integer countLastPageItems() {
        waitForElementClickable(driver, paginationDoubleButton);
        paginationDoubleButton.click();
        List<WebElement> allCategoryCardName = getListWebElement(driver, "(//div[text()='Add new category']//ancestor::button//following-sibling::div//div[contains(@class,'text-primary')])");
        return allCategoryCardName.size();
    }

}
