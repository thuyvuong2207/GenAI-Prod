package actions.commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {

    private final WebDriver driver;

    public BasePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPageURL(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void dismissAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    public void closeAllTabsExceptParent(WebDriver driver, String parentID) {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    public By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    public WebElement getWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(getByXpath(xpathLocator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElements(By.xpath(xpathLocator));
    }

    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    public void sendkeyToElement(WebDriver driver, WebElement element, String textValue) {
        element.clear();
        element.sendKeys(textValue);
    }

    public String getElementText(WebDriver driver, WebElement element) {
        return element.getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, WebElement element, String textItem) {
        Select select = new Select(element);
        select.selectByValue(textItem);
    }

    public String getFirstSelectedItemDefault(WebDriver driver, WebElement element, String textItem) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, WebElement element) {
        Select select = new Select(element);
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String locatorParent, String locatorChild, String expectedTextItem) {
        getWebElement(driver, locatorParent).click();
        sleepInSeconds(2);

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locatorChild)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
                sleepInSeconds(2);
                item.click();
                break;
            }
        }
    }

    public void selectItemInTabList(WebDriver driver, String locatorParent, String locatorChild, String expectedTextItem) {
        getWebElement(driver, locatorParent).click();
        sleepInSeconds(2);

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locatorChild)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
                sleepInSeconds(2);
                item.click();
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, WebElement element, String propertyName) {
        return element.getAttribute(propertyName);
    }

    public String convertRgbaToHexaColor(WebDriver driver, WebElement element) {
        String backgroundColorRGBA = getElementCssValue(driver, element, "background-color");
        return Color.fromString(backgroundColorRGBA).asHex();
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    /**
     * Apply for checkbox and radio buttons
     *
     * @param driver
     * @param element
     */
    public void checkToElement(WebDriver driver, WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    /**
     * Only apply for checkbox
     *
     * @param driver
     * @param element
     */
    public void uncheckToElement(WebDriver driver, WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, WebElement element) {
        return element.isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, WebElement element) {
        return element.isEnabled();
    }

    public void switchToIframe(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void highlightElement(WebDriver driver, WebElement element) {
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        sleepInSeconds(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElementOnDownByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", element);
    }

    public void removeAttributeInDOM(WebDriver driver, WebElement element, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public void sendkeyToElementByJS(WebDriver driver, WebElement element, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public String getAttributeInDOMByJS(WebDriver driver, WebElement element, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", element);
    }

    public String getElementValidationMessage(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    public boolean isImageLoaded(WebDriver driver, WebElement element) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
    }
    public void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForListElementVisible(WebDriver driver, List<WebElement> elementList) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(elementList));
    }
    public void waitForElementInvisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitForListElementInvisible(WebDriver driver, WebElement elementList) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(elementList));
    }
    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public SearchContext getShadowRoot(WebDriver driver){
        WebElement shadowHost = driver.findElement(By.cssSelector("#chrome-extension-genai"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        return shadowRoot;
    }

    public void sleepInSeconds(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

