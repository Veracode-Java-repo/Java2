package testCase;

import org.testng.annotations.Test;

import util.Base.TestBase;
import util.Common.SeleniumUtil;
import util.Data.JsonUtil;

public class demo3 extends TestBase {

	@Test()
	public void demo5TC() throws Exception {
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
	}