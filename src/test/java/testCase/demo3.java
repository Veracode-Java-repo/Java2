package testCase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import util.Base.TestBase;
import util.Common.SeleniumUtil;
import util.Data.JsonUtil;

public class demo3 extends TestBase {

	@Test()
	public void demo31TC() throws Exception {
		String webURL = "https://www.google.com/";
		SeleniumUtil selenium = Selenium(webURL);
		selenium.Log(selenium.getTitle());

		JsonUtil jsonUtil = new JsonUtil(Path.fileFromJsonTestData("test.json"));
		PojoJSON pojo = jsonUtil.getPojo(PojoJSON.class);
		selenium.Log(pojo.getLastName());
		pojo.setLastName("Dhakshna");
		selenium.Log(pojo.getLastName());
		
		selenium.Log(jsonUtil.getValue("firstName"));
		selenium.Log(jsonUtil.getKeys());
		selenium.Log(jsonUtil.getAllValues());
	}
	
	@Test()
	public void demo32TC() throws Exception {
		String webURL = "https://money.rediff.com/gainers";
		SeleniumUtil selenium = Selenium(webURL);
		selenium.Log(selenium.getTitle());
		List<WebElement> elements = selenium.getElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
		System.out.println(elements.size());
		}
	}