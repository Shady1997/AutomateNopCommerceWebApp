package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-login']")
	public WebElement signinButton;

	@FindBy(xpath = "//input[@id='Email']")
	public WebElement userName;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement password;

	@FindBy(xpath = "//input[@id='RememberMe']")
	public WebElement rememberMe;

	@FindBy(xpath = "//button[normalize-space()='Log in']")
	public WebElement loginButton;

}
