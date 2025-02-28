package actions.pageObjects;

import actions.commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AIFoundationPage extends BasePageFactory {
    @FindBy(css = "div#search-bar>input")
    private WebElement searchBar;
    @FindBy(xpath = "(//span[contains(@class,'text-heading')])[2]")
    private WebElement aiFoundationPageTitle;
    @FindBy(xpath = "(//span[contains(@class,'text-heading')])[2]//parent::div//following-sibling::span")
    private WebElement aiFoundationPageSubtitle;

    String aiFoundationModelList = "//div[@class='genai-scrollbar mt-5 h-full overflow-auto']//button[contains(@id,'headlessui-popover-button')]";

    WebDriver driver;

    public AIFoundationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public String getAIFoundationPageTitle() {
        return aiFoundationPageTitle.getText();
    }
    public String getAIFoundationPageSubtitle() {
        return aiFoundationPageSubtitle.getText();
    }
    public List<WebElement> getListOfModel(){
        return getListWebElement(driver,aiFoundationModelList);
    }
    public int getSizeOfModelList(){
        waitForElementVisible(driver,aiFoundationPageTitle);
        return getListOfModel().size();
    }


}
