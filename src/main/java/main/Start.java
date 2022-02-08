package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pom.LoginPage;
import pom.RegisterPage;
import utility.ExcelUtility;
import utility.Utility;

public class Start extends ClassProperties {

	@Parameters("browser")
	@BeforeTest
	private void prepareClassProperties(String browser) throws IOException {
		readProperty = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\properties\\generalProperties.properties");
		Properties prop = new Properties();
		prop.load(readProperty);

		if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + prop.getProperty("firefoxdriver"));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + prop.getProperty("chromedriver"));
			driver = new ChromeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser value!!");
			// Change thread count 1 for sequential , 2 for parallel 3 ..browser..
		}

		js = (JavascriptExecutor) driver;
		loginPage = new LoginPage(driver);
		registerPage = new RegisterPage(driver);
	}

	@Test(priority = 1, groups = "smoke")
	private void startApplication() throws InterruptedException {
		// Mazimize current window
		driver.manage().window().maximize();
		// navigate to website
		driver.get("https://demo.nopcommerce.com/");
		Utility.captureScreenshot(driver, "LoginPage");
	}

	@Test(priority = 2, groups = "smoke")
	private void signUp() throws InterruptedException {
		// click sign up button on home page
		registerPage.signupButton.click();
		// wait for 5 sec
		Thread.sleep(5000);
		// scroll down
		js.executeScript("window.scrollBy(0,100)", "");
		// choose gender
		registerPage.gender.click();
		// add first name
		registerPage.fName.sendKeys(ExcelUtility.getFirstName());
		// add last name
		registerPage.lName.sendKeys(ExcelUtility.getLastName());
		// choose day
		Select chooseDay = new Select(registerPage.day);
		chooseDay.selectByIndex(2);
		// choose month
		Select chooseMonth = new Select(registerPage.month);
		chooseMonth.selectByIndex(2);
		// choose year
		Select chooseYear = new Select(registerPage.year);
		chooseYear.selectByIndex(2);
		// scroll down to login component
		js.executeScript("window.scrollBy(0,100)", "");
		registerPage.email.sendKeys(ExcelUtility.getUserName());
		// add company name
		registerPage.companyName.sendKeys(ExcelUtility.getCompanyName());
		// scroll down to login component
		js.executeScript("window.scrollBy(0,100)", "");
		// recheck newletter
		for (int i = 0; i < 2; i++) {
			registerPage.newsLetter.click();
		}
		// fill password
		registerPage.password.sendKeys(ExcelUtility.getPassword());
		// confirm password
		registerPage.confirmPassword.sendKeys(ExcelUtility.getPassword());
		// click sigin up button
		registerPage.signUpButton.click();
		// click to continue button
		registerPage.continueButton.click();
		// logout
		registerPage.logout.click();
		Thread.sleep(5000);
	}

	@Test(priority = 3, groups = "smoke")
	private void login() throws InterruptedException {
		// click sign in button in home page
		loginPage.signinButton.click();
		// wait for 5 sec
		Thread.sleep(5000);
		// scroll down to login component
		js.executeScript("window.scrollBy(0,300)", "");
		// add username
		loginPage.userName.sendKeys(ExcelUtility.getUserName());
		// add password
		loginPage.password.sendKeys(ExcelUtility.getPassword());
		// check remember me chechbox
		loginPage.rememberMe.click();
		// click login button
		loginPage.loginButton.click();
		// wait for 5 sec
		Thread.sleep(5000);
		// take screenshot to login page
		Utility.captureScreenshot(driver, "verifyLogin");
		// wait for 5 sec
		Thread.sleep(5000);
		// verify login successfully
		Assert.assertEquals(driver.getPageSource().contains("Log out"), true);
	}

	@AfterTest
	private void closeApplication() {
		driver.quit();
	}

	public static void getScreenshotOnFailure() {
		Utility.captureScreenshot(driver, "fail");
	}

}
