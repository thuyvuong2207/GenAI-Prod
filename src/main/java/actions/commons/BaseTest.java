package actions.commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static ExtentReports extent;
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public final Logger log;
    public static final String ZAP_ADDRESS = System.getenv().getOrDefault("ZAP_ADDRESS", "localhost");
    public static final int ZAP_PORT = Integer.parseInt(System.getenv().getOrDefault("ZAP_PORT", "8080"));
    public static final boolean USE_ZAP_PROXY = System.getenv().getOrDefault("USE_PROXY", "false").equals("true");
    public static final boolean HEADLESS = System.getenv().getOrDefault("HEADLESS", "false").equals("true");
    public ClientApi api;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]")
    private WebElement userAccountModal;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]")
    private WebElement userAvatarOnAccountModal;
    @FindBy(xpath = "//div[contains(@class,'settings-header') and contains(@class,'cursor-pointer')]")
    private WebElement userSettingsOnAccountModal;
    @FindBy(xpath = "//div[contains(@class,'pricing-header') and contains(@class,'cursor-pointer')]")
    private WebElement userPricingOnAccountModal;
    @FindBy(xpath = "//div[contains(@class,'logout-header') and contains(@class,'cursor-pointer')]")
    private WebElement userLogoutButtonOnAccountModal;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]//following-sibling::div//span")
    private WebElement userNameOnAccountModal;
    @FindBy(xpath = "//div[contains(@id,'headlessui-popover-panel')]//div[contains(@class,'avatar')]//following-sibling::div//div")
    private WebElement userEmailOnAccountModal;

    public WebDriver getBrowserDriver(String browserName) {
        WebDriver driver;
//        String projectPath = System.getProperty("user.dir");
        String test_url = "https://genai.taureau.ai/";
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        ChromeOptions options = new ChromeOptions();
//        option.addArguments("user-data-dir=/Users/ME/Library/Application Support/Google/Chrome/Profile 4}");
//        option.addArguments("start-maximized");

        // Connect to ZAP proxy
        if (USE_ZAP_PROXY) {
            // Set up zap proxy
            String proxyServer = ZAP_ADDRESS + ":" + ZAP_PORT;
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(proxyServer).setSslProxy(proxyServer);
            options.setProxy(proxy);
            // accept ZAP insecure certs
            options.setAcceptInsecureCerts(true);
            // Set up ZAP client
            api = new ClientApi(ZAP_ADDRESS, ZAP_PORT);
        }
        if (HEADLESS) {
            options.addArguments("--headless");
        }
        switch (browser) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver(options);
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }

        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(test_url);

        return driver;
    }


    public synchronized static ExtentReports createReport() {
        // date today
        Date date = new Date();
        String today = (date.toString().trim());
        System.out.println(date.toString());

        // init HTML Reporter
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testreports/GenAI_Regression.html");
        //init ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //config
        htmlReporter.config().setDocumentTitle("GENAI - Regression Test Results" + today);
        htmlReporter.config().setReportName("GENAI - Regression Test Results" + today);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setTimelineEnabled(true);
        htmlReporter.config().setEncoding("utf-8");

        extent.setSystemInfo("Project", "GenAI");
        extent.setSystemInfo("JDK version", JAVA_VERSION);
        return extent;
    }

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }
    public void tearDown(WebDriver driver) throws ClientApiException {
        driver.quit();
        if (api != null) {
            String title = "ZAP report";
            String template = "risk-confidence-html";
            String theme = null;
            String description = "ZAP security report";
            String reportFileName = "ZAP_Report.html";
            String targetFolder = System.getProperty("user.dir") + "/testreports";

            api.reports.generate(title, template, theme, description, null, null, null, null, null, reportFileName, null, targetFolder, null);
        }
    }
    public boolean isUserPricingDisplayedOnModal() {
        return userPricingOnAccountModal.isDisplayed();
    }
    public boolean isUserSettingsDisplayedOnModal() {
        return userSettingsOnAccountModal.isDisplayed();
    }
    public boolean isLogoutButtonDisplayedOnModal() {
        return userLogoutButtonOnAccountModal.isDisplayed();
    }
    public boolean isUserNameDisplayedOnModal() {
        return userNameOnAccountModal.isDisplayed();
    }
    public boolean isUserEmailDisplayedOnModal() {
        return userEmailOnAccountModal.isDisplayed();
    }
    public boolean isUserAvatarDisplayedOnModal() {
        return userAvatarOnAccountModal.isDisplayed();
    }

}