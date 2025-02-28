package actions.pageObjects;

import actions.commons.BasePageFactory;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage extends BasePageFactory {
    @FindBy(xpath = "//div[text()='Name']//following-sibling::div//input")
    private WebElement userAccountName;
    @FindBy(xpath = "//div[text()='Email address']//following-sibling::div//input")
    private WebElement userAccountEmail;
    @FindBy(xpath = "//span[text()='Save']//ancestor::button")
    private WebElement userAccountButtonSave;
    @FindBy(xpath = "//span[text()='Back']")
    private WebElement userAccountButtonBack;
    @FindBy(xpath = "//img[contains(@src,'genai-logo')]")
    private WebElement homepageButton;
    @FindBy(xpath = "//button[contains(@id,'headlessui-popover-button')]")
    private WebElement userAccountButtonOnHeader;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]//following-sibling::div//span")
    private WebElement userNameOnAccountModal;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]//following-sibling::div//div")
    private WebElement userEmailOnAccountModal;
    @FindBy(xpath = "//div[contains(@class,'logout-header') and contains(@class,'cursor-pointer')]")
    private WebElement userLogoutButtonOnAccountModal;

    WebDriver driver;

    public UserProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getUserAccountNameText() {
        return userAccountName.getAttribute("value");
    }

    public void setNewUserAccountName(WebDriver driver, String newName) {
        setAttributeInDOM(driver, userAccountName, "value", newName);

        setAttributeInDOM(driver, userAccountName, "data-listener-added_836ba302", "true");
    }

    public String getUserAccountEmailText() {
        return userAccountEmail.getAttribute("value");
    }

    public boolean isUserEmailEnabled() {
        return userAccountEmail.isEnabled();
    }

    public HomePage clickHomepageButton() {
        waitForElementClickable(driver, homepageButton);
        homepageButton.click();
        return PageGeneratorManager.getHomePage(driver);
    }

    public boolean isButtonSaveEnabled() {
        return userAccountButtonSave.isEnabled();
    }

    public void clickButtonSave() {
        userAccountButtonSave.click();
    }

    public boolean isButtonBackEnabled() {
        return userAccountButtonBack.isEnabled();
    }

    public HomePage clickButtonBack() {
        userAccountButtonBack.click();
        return PageGeneratorManager.getHomePage(driver);
    }

    public void clickUserAccountButton(WebDriver driver) {
        waitForElementClickable(driver, userAccountButtonOnHeader);
        setAttributeInDOM(driver, userAccountButtonOnHeader, "data-headlessui-state", "open active");
    }
    public LoginPage clickLogoutButton(){
        waitForElementVisible(driver, userLogoutButtonOnAccountModal);
        userLogoutButtonOnAccountModal.click();
        return PageGeneratorManager.getLoginPage(driver);
    }

    public String getUserAccountNameTextOnModal() {
        return userNameOnAccountModal.getText();
    }

    public String getUserAccountEmailTextOnModal() {
        return userEmailOnAccountModal.getText();
    }

}
