package testCase;

import org.testng.annotations.Test;

import config.TestNG.Author;
import util.Base.TestBase;
import util.Common.SeleniumUtil;

public class demo2 extends TestBase {

	@Author(Name = "Vignesh")
	@Test()
	public void saucedemo3() {
		String webURL = "https://www.saucedemo.com/";
		SeleniumUtil selenium = Selenium(webURL);

		
		System.out.println(Locators.get("username_xpath"));
		System.out.println(Locators.get("passwrod_id"));


	
	}
	
}
