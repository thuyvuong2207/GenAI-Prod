package actions.pageObjects;

import actions.commons.BasePageFactory;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePageFactory {
    @FindBy(css = "input#login-input-email")
    private WebElement loginEmailInput;
    @FindBy(css = "input#login-input-password")
    private WebElement loginPasswordInput;
    @FindBy(css = "button#login-btn-continue")
    private WebElement loginButton;
    @FindBy(css = "span#login-btn-forgot")
    private WebElement forgotPasswordButton;
    @FindBy(css = "button#login-btn-continue-w-google")
    private WebElement loginWithGoogleButton;
    @FindBy(xpath = "//div[text()='Register']")
    private WebElement registerButton;
    @FindBy(id = "login-input-email-error-inline-mess")
    private WebElement alertEmail;

     WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public boolean isEmailFieldDisplayed(){
        return loginEmailInput.isDisplayed();
    }
    public boolean isPasswordFieldDisplayed(){
        return loginPasswordInput.isDisplayed();
    }
    public String getLoginPageTitle() {
        return getPageTitle(driver);
    }
    public String getLoginEmailText() {
        return loginEmailInput.getText();
    }
    public String getLoginPasswordText() {
        return loginPasswordInput.getText();
    }
    public boolean isForgotPasswordButtonVisible() {
        return forgotPasswordButton.isDisplayed();
    }
    public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }
    public void clearLoginEmail(){
        waitForElementVisible(driver, loginEmailInput);
        loginEmailInput.clear();
    }
    public void inputLoginEmail(String inputValue){
        waitForElementVisible(driver, loginEmailInput);
        loginEmailInput.sendKeys(inputValue);
    }
    public void inputLoginPassword(String inputValue){
        waitForElementVisible(driver, loginPasswordInput);
        loginPasswordInput.sendKeys(inputValue);
    }
    public HomePage clickLoginButton() {
        waitForElementClickable(driver, loginButton);
        loginButton.click();
        return PageGeneratorManager.getHomePage(driver);
    }
    public void clickLoginWithGoogleButton(WebElement element) {
        waitForElementClickable(driver, element);
        loginWithGoogleButton.click();
    }
    public void clickRegisterButton(WebElement element) {
        waitForElementClickable(driver, element);
        registerButton.click();
    }
    public void clickForgotPwButton(WebElement element) {
        waitForElementClickable(driver, element);
        forgotPasswordButton.click();
    }
    public boolean isEmailAlertMsgDisplayed() {
        return alertEmail.isDisplayed();
    }
    public String getEmailAlertMsgText() {
        return alertEmail.getText();
    }
}
