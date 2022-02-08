package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-register']")
	public WebElement signupButton;

	@FindBy(xpath = "//input[@id='gender-male']")
	public WebElement gender;

	@FindBy(xpath = "//input[@id='FirstName']")
	public WebElement fName;

	@FindBy(xpath = "//input[@id='LastName']")
	public WebElement lName;

	@FindBy(xpath = "//select[@name='DateOfBirthDay']")
	public WebElement day;

	@FindBy(xpath = "//select[@name='DateOfBirthMonth']")
	public WebElement month;

	@FindBy(xpath = "//select[@name='DateOfBirthYear']")
	public WebElement year;

	@FindBy(xpath = "//input[@id='Email']")
	public WebElement email;

	@FindBy(xpath = "//input[@id='Company']")
	public WebElement companyName;

	@FindBy(xpath = "//input[@id='Newsletter']")
	public WebElement newsLetter;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement password;

	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	public WebElement confirmPassword;

	@FindBy(xpath = "//button[@id='register-button']")
	public WebElement signUpButton;
	
	@FindBy(xpath = "//a[@class='button-1 register-continue-button']")
	public WebElement continueButton;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	public WebElement logout;
}
