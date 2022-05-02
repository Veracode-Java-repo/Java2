package util.Common;

import org.testng.ITestResult;
import org.testng.Reporter;

import config.Capability.ClassInit;
import config.Capability.Driver;
import config.Capability.Path;
import config.Report.ExtentReportManger;
import config.Report.ScreenShot;
import config.TestNG.Author;

public class BaseUtil {

	protected static ClassInit classInit;
	protected static Path Path;
	protected static ExtentReportManger Extent;
	protected static Driver Driver;
	protected static ScreenShot screenShot;
	protected static String BrowserName;
	protected static String BrowserStack;
	protected static int waitTime;

	protected static ThreadLocal<String> testType = new ThreadLocal<String>();

	protected static ThreadLocal<SeleniumUtil> Selenium = new ThreadLocal<SeleniumUtil>();
	protected static SeleniumUtil Selenium(String webURL) {
		Extent.StartExtentTest(getInfo(Reporter.getCurrentTestResult()));
		testType.set("Selenium");
		Driver = new Driver();
		Selenium.set(new SeleniumUtil(Driver.getDriver(), Extent.getTestThread(), BrowserName, webURL, waitTime));
		return Selenium.get();
	}

	
	protected static ThreadLocal<RestAPIUtil> RestAPI = new ThreadLocal<RestAPIUtil>();
	protected static RestAPIUtil RestAPI(String baseURI) {
		Extent.StartExtentTest(getInfo(Reporter.getCurrentTestResult()));
		testType.set("RestAPI");
		RestAPI.set(new RestAPIUtil(baseURI, Extent.getTestThread()));
		return RestAPI.get();
	}

	public static String[] getInfo(ITestResult result) {
		String file = "<i class=\"fa fa-file-code-o\" aria-hidden=\"true\"></i> ";
		String folder = " <i class=\"fa fa-folder-open-o\" aria-hidden=\"true\"></i> ";
		String testcaseName = result.getName();
		String classpackName =result.getTestClass().getName();
		String tcpName = file+ testcaseName +folder+classpackName ;
		try {
		String author = Reporter.getCurrentTestResult().getMethod().getConstructorOrMethod().getMethod().getAnnotation(Author.class).Name().toString();
		String[] infoNames = {tcpName,classpackName,author};
		return infoNames;
		
		} catch (Exception e) {
			String[] infoNames = {tcpName,classpackName};
			return infoNames;
		}
	}
	
}
