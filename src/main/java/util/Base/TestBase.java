package util.Base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import config.Capability.ClassConfig;
import util.Common.BaseUtil;
import util.Data.ExcelUtil;
import util.Data.JsonUtil;
import util.Data.PropertiesUtil;
import util.Data.TextUtil;

public class TestBase extends BaseUtil {
	
	@BeforeSuite(alwaysRun = true)
	public void initialize() {
		classInit = new ClassConfig();
		classInit.BeforeSuite();
	}

	@AfterMethod(alwaysRun = true)
	public void quitBrowser(ITestResult result) {
		if(testType.get()!=null) {
		classInit.AfterMethod(result, testType.get());
		}
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		classInit.AfterSuite();
	}
	
	public PropertiesUtil fileFromProperties(String fileName) {
		String path = pathRoot.fileFromProperties(fileName);
		return new PropertiesUtil (path) ;
		
	}
	
	public ExcelUtil fileFromExcelTestData(String fileName) {
		String path = pathRoot.fileFromExcelTestData(fileName);
		return new ExcelUtil (path) ;
		
	}
	
	public JsonUtil fileFromJsonTestData(String fileName) {
		String path = pathRoot.fileFromJsonTestData(fileName);
		return new JsonUtil (path) ;
		
	}
	
	public TextUtil fileFromTextTestData(String fileName) {
		String path = pathRoot.fileFromTextTestData(fileName);
		return new TextUtil (path) ;
		
	}

}


//ScreenRecorderUtil videoRecorder = new ScreenRecorderUtil(selenium.getDriver());
//videoRecorder.setRecorder(videoRecorder);
//videoRecorder.getRecorder().startRecording("articleSearch");
//try {
//	videoRecorder.getRecorder().stopRecording();
//} catch (InterruptedException e) {
//	e.printStackTrace();
//}