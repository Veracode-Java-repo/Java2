package config.Capability;

import java.io.File;
import org.testng.ITestResult;

import config.Report.ExtentReportManger;
import config.Report.ScreenShot;
import util.Common.BaseUtil;
import util.Data.PropertiesUtil;

public class ClassInit extends BaseUtil {

	public void BeforeSuite() {
		System.out.println(
				"\n===============================================\n\nTest Suite Started\n\n===============================================");
		Path = new Path();
		dirCheck();
		Extent = new ExtentReportManger();
		screenShot = new ScreenShot();
		PropertiesUtil propUtil = new PropertiesUtil(Path.fileFromProperties("browser.properties"));
		BrowserName = propUtil.getValue("BrowserName").toLowerCase();
		waitTime = Integer.parseInt(propUtil.getValue("waitTime"));
		screenShot.cleanFolders();
		Extent.StartExtentReport();

	}

	public void AfterMethod(ITestResult result, String testType) {
		if (result.getStatus() == ITestResult.FAILURE) {
			screenShot.ExtentFailShot(Selenium.get().getDriver(), result, Extent.getTestThread());
		} else if (result.getStatus() == ITestResult.SKIP) {
			screenShot.ExtentSkipShot(Selenium.get().getDriver(), result, Extent.getTestThread());
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
//		try {
//			System.out.println(Jsoup.parse(new File(pathUtil.fileFromExtentReport("ExtentReport.html")), "UTF-8").toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void dirCheck() {
		
		File suitesFile = new File(Path.getSuitesXMLPath());
		if (!suitesFile.isDirectory()) {
			suitesFile.mkdir();
		}
		
		File propFile = new File(Path.getPropertiesPath());
		if (!propFile.isDirectory()) {
			propFile.mkdir();
		}

		File testDataFile = new File(Path.getTestDataPath());
		if (!testDataFile.isDirectory()) {
			testDataFile.mkdir();
		}

		File ExcelFile = new File(Path.getExcelTestDataPath());
		if (!ExcelFile.isDirectory()) {
			ExcelFile.mkdir();
		}

		File JsonFile = new File(Path.getJsonTestDataPath());
		if (!JsonFile.isDirectory()) {
			JsonFile.mkdir();
		}

		File TextFile = new File(Path.getTextTestDataPath());
		if (!TextFile.isDirectory()) {
			TextFile.mkdir();
		}
		

		File OtherFile = new File(Path.getOtherTestDataPath());
		if (!OtherFile.isDirectory()) {
			OtherFile.mkdir();
		}

		File reportscreenshotFile = new File(Path.getReportScreenShotPath());
		if (!reportscreenshotFile.isDirectory()) {
			reportscreenshotFile.mkdir();
		}

		File ExtentReportFile = new File(Path.getExtentReportPath());
		if (!ExtentReportFile.isDirectory()) {
			ExtentReportFile.mkdir();
		}

		File screenshotFile = new File(Path.getScreenShotPath());
		if (!screenshotFile.isDirectory()) {
			screenshotFile.mkdir();
		}

		File screenshotPassedFile = new File(Path.getPassedShotPath());
		if (!screenshotPassedFile.isDirectory()) {
			screenshotPassedFile.mkdir();
		}

		File screenshotFailedFile = new File(Path.getFailedShotPath());
		if (!screenshotFailedFile.isDirectory()) {
			screenshotFailedFile.mkdir();
		}

		File screenshotSkippedFile = new File(Path.getSkippedShotPath());
		if (!screenshotSkippedFile.isDirectory()) {
			screenshotSkippedFile.mkdir();
		}

	}
}
