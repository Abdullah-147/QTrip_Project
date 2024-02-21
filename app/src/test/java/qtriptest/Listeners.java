package qtriptest;

import qtriptest.tests.BaseTest;
import java.io.File;
import java.io.IOException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener{

    static ExtentReports reports;
    ExtentTest test;
    @Override
    public void onStart(ITestContext c){
        reports=ReportSingleton.createExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result){
       test= reports.startTest(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        try {
            test.log(LogStatus.FAIL,test.addScreenCapture(screenCapture(BaseTest.driver,result.getName(),"FAIL")) +result.getName()+" failed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        reports.endTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result){
        try {
            test.log(LogStatus.PASS,test.addScreenCapture(screenCapture(BaseTest.driver,result.getName(),"PASS")) +result.getName()+" passed.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        reports.endTest(test);
    }

    @Override
    public void onTestSkipped(ITestResult result){
        test.log(LogStatus.SKIP,result.getName()+" skipped.");
        reports.endTest(test);
    }

    @Override
    public void onFinish(ITestContext c){
        reports.flush();
    }

    public static String screenCapture(WebDriver driver,String testName,String status) throws IOException{
        File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destFile=new File("./ScreenCaptures/"+testName+System.currentTimeMillis()+status+".png");
        FileUtils.copyFile(srcfile, destFile);
        return destFile.getAbsolutePath();
    }
}
