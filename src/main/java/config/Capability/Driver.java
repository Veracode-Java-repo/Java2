package config.Capability;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.Common.BaseUtil;
import util.Data.PropertiesUtil;

public class Driver extends BaseUtil {

	ThreadLocal<WebDriver> weDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			WebDriver dov = null;
			if (BrowserStack.equalsIgnoreCase("on")) {
				dov = browserStackDriver();
			}
			else {
				if (BrowserName.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					dov = new FirefoxDriver();
				} else if (BrowserName.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					dov = new ChromeDriver();
				} else if (BrowserName.equalsIgnoreCase("edge")) {
					WebDriverManager.edgedriver().setup();
					dov = new EdgeDriver();
				} else if (BrowserName.equalsIgnoreCase("opera")) {
					WebDriverManager.operadriver().setup();
					dov = new OperaDriver();
				} else if (BrowserName.equalsIgnoreCase("firefoxheadless")) {
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.addArguments("--headless");
					WebDriverManager.firefoxdriver().setup();
					dov = new FirefoxDriver(firefoxOptions);
				} else if (BrowserName.equalsIgnoreCase("chromeheadless")) {
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("--headless");
					WebDriverManager.chromedriver().setup();
					dov = new ChromeDriver(chromeOptions);
				} else {
					System.out.println("Update Proper BrowserName in File");
				}
			}

			return dov;
		}
	};

	public WebDriver getDriver() {
		return weDriver.get();
	}

	public void quitDriver() {
		getDriver().quit();
		weDriver.remove();
	}

	public RemoteWebDriver browserStackDriver() {
		PropertiesUtil propertiesUtil = new PropertiesUtil (Path.fileFromProperties("browserStack.properties")) ;
		  String USERNAME = propertiesUtil.getValue("USERNAME");
		  String ACCESS_KEY = propertiesUtil.getValue("ACCESS_KEY");
		  String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
		  RemoteWebDriver remoteWebDriver = null;

		 
		  
		  DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("os", propertiesUtil.getValue("os"));
		    caps.setCapability("os_version", propertiesUtil.getValue("os_version"));
		    caps.setCapability("browser", propertiesUtil.getValue("browser"));
		    caps.setCapability("browser_version", propertiesUtil.getValue("browser_version"));
		    caps.setCapability("resolution", "1920x1080");
		    caps.setCapability("name", Reporter.getCurrentTestResult().getName()); 
		    caps.setCapability("build", Reporter.getCurrentTestResult().getTestClass().getName()); 
		    try {
			remoteWebDriver = new RemoteWebDriver(new URL(URL), caps);
			} catch (Exception e) {
				 if (USERNAME.isBlank() || USERNAME.isEmpty()) {
					  System.out.println("BROWSERSTACK_USERNAME is Blank");
				  }
				  
				 else if (ACCESS_KEY.isBlank() || ACCESS_KEY.isEmpty()) {
					  System.out.println("BROWSERSTACK_ACCESS_KEY is Blank");
				  } else {
						System.out.println("BROWSERSTACK_ERROR = Check Username / Password / OS / Device");

				  }
				  
				System.out.println("FILE_LOCATION = "+Path.fileFromProperties("browserStack.properties"));
				e.printStackTrace();
			}
		    return remoteWebDriver;
	}
}
