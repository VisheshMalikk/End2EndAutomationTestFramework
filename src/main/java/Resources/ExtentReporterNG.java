package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	// Meta Data (like report name, title name) for our Extent Report setup
	public static ExtentReports getReportObject() {

		String testReportPath = System.getProperty(("user.dir") + "\\reports\\index.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(testReportPath);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("SDET", "Vishesh Malik");

		return extent;
	}

}
