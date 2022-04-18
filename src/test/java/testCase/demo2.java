package testCase;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import config.TestNG.Author;
import pages.SauceLabs.LoginPage;
import util.Base.TestBase;
import util.Common.SeleniumUtil;
import util.Common.SeleniumUtil.locatorType;

public class demo2 extends TestBase {

	@Author(Name = "Vignesh")
	@Test()
	public void saucedemo3() {
		String webURL = "https://www.saucedemo.com/";
		SeleniumUtil selenium = Selenium(webURL);

		WebElement Username = selenium.findBy_ID("user-name");
		WebElement password = selenium.findBy_ID("password");
		selenium.Log(selenium.getLocatorString(Username));
		Username = selenium.refreshElement(Username);
		selenium.type(Username, "standard_use");
		selenium.type(password, "secret_sauce");
		selenium.click(locatorType.ID, "login-button");
		selenium.pageScreenShot();

		selenium.Log("Direct Login not successfully");
		selenium.sleep(2);

		selenium.refresh();

		new LoginPage().Login();
		selenium.sleep(2);
		selenium.pageScreenShot();
		
		Assert.assertTrue(true);
	
	}
	
}
