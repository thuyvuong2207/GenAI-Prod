package actions.pageObjects;

import actions.commons.BasePageFactory;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPricingPage extends BasePageFactory {
    @FindBy(xpath = "(//span[contains(@class,'text-title')])[1]")
    private WebElement userPricingPageTitle;

    @FindBy(xpath = "//img[contains(@src,'genai-logo')]")
    private WebElement homepageButton;

    WebDriver driver;
    public UserPricingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public String getUserPricingPageTitle() {
        return userPricingPageTitle.getText();
    }

    public HomePage clickHomepageButton() {
        waitForElementClickable(driver,homepageButton);
        homepageButton.click();
        return PageGeneratorManager.getHomePage(driver);
    }
}
