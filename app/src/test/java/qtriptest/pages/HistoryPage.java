
package qtriptest.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qtriptest.SeleniumWrapper;

public class HistoryPage extends SeleniumWrapper {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//table/tbody/tr[1]/th[1]")
	List<WebElement> transanctionId;

	@FindBy(xpath = "//table//tbody//th")
	List<WebElement> reservations;

	@FindBy(xpath = "//table/tbody/tr[1]/td[8]//button")
	WebElement cancelAdventureButton;

	@FindBy(xpath = "//*[text()='Logout']")
	WebElement logoutButton;

	@FindBy(xpath = "//h1[text()='Your Reservations']")
	WebElement reservationHeading;

	public HistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void cancelReservation() throws InterruptedException {
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
		}
		custom_click(driver, cancelAdventureButton);
		Thread.sleep(2000);
	}

	public String getTransactionID() {
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
		}
		wait.until(ExpectedConditions.visibilityOf(reservationHeading));
		return transanctionId.get(0).getText();
	}

	public int ifReservationExists() {
		return reservations.size();
	}

	public void clickOnLogout() {
		custom_click(driver, wait.until(ExpectedConditions.elementToBeClickable(logoutButton)));
	}

	public Boolean isAlertPresent() {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			if (wait.until(ExpectedConditions.alertIsPresent()) == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			return false;
		}
	}
}
