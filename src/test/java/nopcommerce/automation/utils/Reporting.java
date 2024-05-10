package nopcommerce.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reporting extends TestListenerAdapter {
    ExtentReports extent;
    ExtentTest test;
    private final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    final File CONFIG = new File("Config/extent-config.json");

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String reportName = "spark-report.html";
//        String reportName = "spark-report-" + timeStamp + ".html";

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/" + reportName);
        try {
            spark.loadJSONConfig(CONFIG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult tr) {
        test = extent.createTest(tr.getName());

        if(tr.getMethod().getGroups().length > 0) {
            test.assignCategory(tr.getMethod().getGroups());
        }

        if(tr.getMethod().getDescription() != null) {
            String description = tr.getMethod().getDescription();
            test.info(MarkupHelper.createLabel(description, ExtentColor.BLUE));
        }

        if(tr.getParameters().length > 0){
            List<Object> parameters = List.of(tr.getParameters());
            test.info(MarkupHelper.createUnorderedList(parameters).getMarkup());
        }

        extentTest.set(test);
    }


    @Override
    public void onTestSuccess(ITestResult tr) {
        extentTest.get().pass(MarkupHelper.createLabel(tr.getName() + " PASSED", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        ExtentTest currentTest = extentTest.get();
        currentTest.fail(MarkupHelper.createLabel(tr.getName() + " FAILED", ExtentColor.RED));
        currentTest.fail(tr.getThrowable());

        WebDriver driver = (WebDriver) tr.getTestContext().getAttribute("WebDriver");
        if (driver != null) {
            String screenshotPath = captureScreenshot(driver, tr.getName());
            System.out.println("Screenshot path: " + screenshotPath);
            currentTest.fail("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        extentTest.get().skip(MarkupHelper.createLabel(tr.getName() + " SKIPPED", ExtentColor.ORANGE));
        extentTest.get().skip(tr.getThrowable());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

    private String captureScreenshot(WebDriver driver, String screenshotName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "screenshots/" + screenshotName + ".png";
        File dest = new File(path);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
