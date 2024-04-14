package qtriptest;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {
    private static ExtentReports reports;
    private ReportSingleton() {};

    public static ExtentReports createExtentReport(){
    	if(reports!=null) return reports;
        reports=new ExtentReports("./ExtentReport.html");
        reports.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));
        return reports;
    }
}