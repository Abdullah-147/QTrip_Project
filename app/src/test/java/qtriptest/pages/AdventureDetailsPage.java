package qtriptest.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qtriptest.SeleniumWrapper;

public class AdventureDetailsPage extends SeleniumWrapper {

	WebDriver driver;
	WebDriverWait wait;

	public AdventureDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@FindBy(id = "adventure-name")
	WebElement adventureDetailsHeader;

	@FindBy(css = "[name=name]")
	WebElement name;

	@FindBy(css = "[name=date]")
	WebElement date;

	@FindBy(css = "[name=person]")
	WebElement count;

	@FindBy(css = ".reserve-button")
	WebElement reserveButton;

	@FindBy(css = "#reserved-banner")
	WebElement successMsg;

	@FindBy(linkText = "Reservations")
	WebElement reservationsButton;

	public Boolean ifLandedOnAdventureDetailsPage() {
		return wait.until(ExpectedConditions.visibilityOf(adventureDetailsHeader)).isDisplayed();
	}

	public void setName(String Name) {
		wait.until(ExpectedConditions.visibilityOf(name)).clear();
		custom_sendKeys(Name, name);
	}

	public void setDate(String Date) {
		custom_sendKeys(Date.split("-")[0] + Date.split("-")[1] + Date.split("-")[2], date);
	}

	public void setCount(String Count) {
		custom_sendKeys(Count, count);
	}

	public void clickReserve() {
		custom_click(driver, reserveButton);
	}

	public Boolean isAdventureReserved() {
		return wait.until(ExpectedConditions.visibilityOf(successMsg)).getText().contains("successful");
	}

	public void checkReservedBooking() {
		custom_click(driver, wait.until(ExpectedConditions.visibilityOf(reservationsButton)));
	}

}