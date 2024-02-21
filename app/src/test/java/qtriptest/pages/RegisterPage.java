package qtriptest.pages;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qtriptest.SeleniumWrapper;

public class RegisterPage extends SeleniumWrapper {
	@FindBy(css = "input[name=email]")
	WebElement email_inputBox;

	@FindBy(css = "input[name=password]")
	WebElement password_inputBox;

	@FindBy(css = "input[name=confirmpassword]")
	WebElement cnfmpassword_textbox;

	@FindBy(xpath = "//button[contains(text(),'Register')]")
	WebElement register_Button;

	WebDriver driver;
	public static String userEmail;
	WebDriverWait wait;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void registerStaticUser(String email, String password, String confrimPassword) {
		wait.until(ExpectedConditions.visibilityOf(email_inputBox));
		userEmail = email;
		custom_sendKeys(userEmail, email_inputBox);
		custom_sendKeys(password, password_inputBox);
		custom_sendKeys(confrimPassword, cnfmpassword_textbox);
		custom_click(driver, register_Button);
	}

	public void registerDynamicUser(String userName, String password) {
		wait.until(ExpectedConditions.visibilityOf(email_inputBox));
		userEmail = userName.split("@")[0] + UUID.randomUUID().toString() + "@" + userName.split("@")[1];
		custom_sendKeys(userEmail, email_inputBox);
		custom_sendKeys(password, password_inputBox);
		custom_sendKeys(password, cnfmpassword_textbox);
		custom_click(driver, register_Button);
	}

}
