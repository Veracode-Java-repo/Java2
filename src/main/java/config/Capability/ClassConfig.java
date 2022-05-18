package config.Capability;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import config.Report.ExtentReportManger;
import config.Report.ScreenShot;
import util.Common.BaseUtil;
import util.Data.PropertiesUtil;

public class ClassConfig extends BaseUtil {

	public void BeforeSuite() {
		System.out.println(
				"\n===============================================\n\nTest Suite Started\n\n===============================================");
		pathRoot = new PathConfig();
		dirCheck();
		Extent = new ExtentReportManger();
		screenShot = new ScreenShot();
		PropertiesUtil propUtil = new PropertiesUtil(pathRoot.fileFromProperties("browser.properties"));
		BrowserName = propUtil.getValue("BrowserName").toLowerCase();
		BrowserStack = propUtil.getValue("BrowserStack").toLowerCase();
		waitTime = Integer.parseInt(propUtil.getValue("waitTime"));
		screenShot.cleanFolders();
		Extent.StartExtentReport();

	}

	public void AfterMethod(ITestResult result, String testType) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			if(BrowserStack.equalsIgnoreCase("on")) {
				markTestStatus("passed", "Passed : "+result.getName(), Selenium.get().getDriver());
			}
			
		} else if (result.getStatus() == ITestResult.FAILURE) {
			screenShot.ExtentFailShot(Selenium.get().getDriver(), result, Extent.getTestThread());
			if(BrowserStack.equalsIgnoreCase("on")) {
				markTestStatus("failed", "Failed : "+result.getThrowable(), Selenium.get().getDriver());
			}
			
		} else if (result.getStatus() == ITestResult.SKIP) {
			screenShot.ExtentSkipShot(Selenium.get().getDriver(), result, Extent.getTestThread());
			if(BrowserStack.equalsIgnoreCase("on")) {
				markTestStatus("failed", "Skipped : "+result.getThrowable(), Selenium.get().getDriver());
			}
		}

		if (testType.equalsIgnoreCase("Selenium")) {
			Driver.quitDriver();
			Selenium.remove();
			Extent.removeThread();
		} else if (testType.equalsIgnoreCase("RestAPI")) {
			RestAPI.remove();
			Extent.removeThread();
		} else {
			System.out.println("testType is Wrong : " + testType);
		}
	}

	public void AfterSuite() {
		Extent.EndExtentReport();
		System.out.println("\n===============================================\n\nTest Suite Completed");
	}

	public void dirCheck() {
		
		File suitesFile = new File(pathRoot.getSuitesXMLPath());
		if (!suitesFile.isDirectory()) {
			suitesFile.mkdir();
		}
		
		File propFile = new File(pathRoot.getPropertiesPath());
		if (!propFile.isDirectory()) {
			propFile.mkdir();
		}

		File testDataFile = new File(pathRoot.getTestDataPath());
		if (!testDataFile.isDirectory()) {
			testDataFile.mkdir();
		}

		File ExcelFile = new File(pathRoot.getExcelTestDataPath());
		if (!ExcelFile.isDirectory()) {
			ExcelFile.mkdir();
		}

		File JsonFile = new File(pathRoot.getJsonTestDataPath());
		if (!JsonFile.isDirectory()) {
			JsonFile.mkdir();
		}

		File TextFile = new File(pathRoot.getTextTestDataPath());
		if (!TextFile.isDirectory()) {
			TextFile.mkdir();
		}
		

		File OtherFile = new File(pathRoot.getOtherTestDataPath());
		if (!OtherFile.isDirectory()) {
			OtherFile.mkdir();
		}

		File reportscreenshotFile = new File(pathRoot.getReportScreenShotPath());
		if (!reportscreenshotFile.isDirectory()) {
			reportscreenshotFile.mkdir();
		}

		File ExtentReportFile = new File(pathRoot.getExtentReportPath());
		if (!ExtentReportFile.isDirectory()) {
			ExtentReportFile.mkdir();
		}

		File screenshotFile = new File(pathRoot.getScreenShotPath());
		if (!screenshotFile.isDirectory()) {
			screenshotFile.mkdir();
		}

		File screenshotPassedFile = new File(pathRoot.getPassedShotPath());
		if (!screenshotPassedFile.isDirectory()) {
			screenshotPassedFile.mkdir();
		}

		File screenshotFailedFile = new File(pathRoot.getFailedShotPath());
		if (!screenshotFailedFile.isDirectory()) {
			screenshotFailedFile.mkdir();
		}

		File screenshotSkippedFile = new File(pathRoot.getSkippedShotPath());
		if (!screenshotSkippedFile.isDirectory()) {
			screenshotSkippedFile.mkdir();
		}

	}
	
	public void markTestStatus(String status, String reason, WebDriver WEdriver) {
	    final JavascriptExecutor jse = (JavascriptExecutor) WEdriver;
	    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+ status + "\", \"reason\": \"" + reason + "\"}}");
	  }
}
