package testcases.feature_Extension;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.ExtensionOptionPage;
import actions.pageObjects.HomePage;
import actions.pageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Extension_Sidebar extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ExtensionOptionPage extensionOptionPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass() {
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
    public void Verify_Default_Sidebar_Display() {
        // Step:
        // Login and go to Dashboard
        // Onclick Extension icon
        // Verify display default sidebar
        // Verify info on sidebar (wf types, wf list)

        extensionOptionPage = homePage.goToExtensionOptionPage();
        extensionOptionPage.clickModeUsePopup(driver);
        extensionOptionPage.clickModeAlwaysOn(driver);
        homePage = extensionOptionPage.switchToGenAIWindow(driver);
        SearchContext shadowRoot = homePage.getShadowRoot(driver);
        WebElement extensionFloatIcon = shadowRoot.findElement(By.cssSelector("div[class*='button-panel-extension']"));
        extensionFloatIcon.click();
        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("span[class*='text-heading'")).getText(),"Hi,");
        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("span[class*='text-subBody'")).getText(),"How can I assist you today?");
        Assert.assertTrue(shadowRoot.findElement(By.xpath("//span[text()='Start a conversation workflow']")).isDisplayed());
        Assert.assertTrue(shadowRoot.findElement(By.xpath("//span[text()='Run an automation process']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        extent.flush();
        driver.quit();
    }
}
