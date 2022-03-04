package config.Capability;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.Common.BaseUtil;

public class Driver extends BaseUtil {

	ThreadLocal<WebDriver> weDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			WebDriver dov = null;
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

}
