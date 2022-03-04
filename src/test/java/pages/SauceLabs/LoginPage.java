package pages.SauceLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.Base.PageBase;

public class LoginPage extends PageBase {

	public LoginPage() {
		super();
	}

	private By Username = By.id("user-name");
	@FindBy(id = "password") private WebElement password;
	@FindBy(id = "login-button") private WebElement loginbutton;

	public LoginPage Login(String UserNa, String UserPas) {
		selenium.type(Username, UserNa);
		selenium.type(password, UserPas);
		selenium.click(loginbutton);
		selenium.Log("Login Failed");
		return this;

	}

	public HomePage Login() {
		selenium.type(Username, "standard_user");
		selenium.type(password, "secret_sauce");
		selenium.click(loginbutton);
		selenium.Log("Direct Login successfully");
		return new HomePage();

	}

}
