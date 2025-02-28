package actions.reportConfig;

import actions.commons.BaseTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static actions.reportConfig.ExtentTestManager.getTest;


public class TestListeners extends BaseTest implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("---------- " + context.getName() + " STARTED TEST ----------");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("---------- " + context.getName() + " FINISHED TEST ----------");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("---------- " + result.getName() + " STARTED TEST ----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        getTest().log(Status.PASS, MarkupHelper
                .createLabel(testName.toUpperCase() + "--- PASSED", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        getTest().log(Status.FAIL, MarkupHelper
                .createLabel(testName.toUpperCase() + "--- FAILED", ExtentColor.RED));
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getBrowserDriver("chrome");

        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Screenshot and Exception", result.getThrowable(), getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SKIPPED TEST ----------");
    }
}
