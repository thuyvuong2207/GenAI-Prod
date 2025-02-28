package actions.pageObjects;

import actions.commons.BasePageFactory;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExtensionOptionPage extends BasePageFactory {
    @FindBy(xpath = "//div[text()='General']")
    private WebElement tabGeneral;
    @FindBy(xpath = "//div[text()='Sidebar']")
    private WebElement tabSidebar;
    @FindBy(xpath = "//div[text()='Context menu']")
    private WebElement tabContextMenu;
    @FindBy(xpath = "//span[text()='Use pop-up']/preceding-sibling::button")
    private WebElement buttonModeUsePopup;
    @FindBy(xpath = "//span[text()='Use pop-up']/preceding-sibling::button")
    private WebElement buttonModeAlwaysOn;

    WebDriver driver;

    public ExtensionOptionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    String ExtensionOptionURL = "chrome-extension://knldjmfmopnpolahpmmgbagdohdnhkik/options/index.html";

    public void goToExtensionOptionPage(WebDriver driver){
        driver.get(ExtensionOptionURL);
        sleepInSeconds(3);
    }
    public void openTabSidebar(WebDriver driver){
        waitForElementClickable(driver, tabSidebar);
        tabSidebar.click();
    }
    public void clickModeUsePopup(WebDriver driver){
        waitForElementClickable(driver, buttonModeUsePopup);
        buttonModeUsePopup.click();
    }
    public void clickModeAlwaysOn(WebDriver driver){
        waitForElementClickable(driver, buttonModeAlwaysOn);
        buttonModeAlwaysOn.click();
    }
    public HomePage switchToGenAIWindow(WebDriver driver){
        switchToWindowByTitle(driver, "GenAI");
        sleepInSeconds(2);
        return PageGeneratorManager.getHomePage(driver);
    }
    public ExtensionOptionPage switchToExtensionOptionsWindow(WebDriver driver){
        switchToWindowByTitle(driver, "Options");
        sleepInSeconds(2);
        return PageGeneratorManager.getExtensionOptionPage(driver);
    }

}
