package qtriptest.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import qtriptest.SeleniumWrapper;

public class AdventurePage extends SeleniumWrapper {
	WebDriver driver;
	static String pageTitle = "QTrip Adventures";
	WebDriverWait wait;

	@FindBy(tagName = "h1")
	WebElement adventures;

	@FindBy(xpath = "//div[contains(text(),'Clear')]")
	List<WebElement> clearButtons;

	@FindBy(id = "duration-select")
	WebElement durationFilter;

	@FindBy(id = "category-select")
	WebElement categoryFilter;

	@FindBy(id = "search-adventures")
	WebElement searchAdventure;

	@FindBy(xpath = "//div[@id='data']//a")
	List<WebElement> filteredResults;

	@FindBy(xpath = "//div[@id='data']//a")
	List<WebElement> unfilteredResults;

	public AdventurePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public Boolean ifLandedOnAdventuresPage() {
		return wait.until(ExpectedConditions.titleIs(pageTitle));
	}

	public void clearAllFilters() {
		for (WebElement clearbutton : clearButtons) {
			custom_click(driver, clearbutton);
		}
	}

	public void applyDurationFilter(String duration) {
		Select select = new Select(durationFilter);
		select.selectByVisibleText(duration);
	}

	public void applyCategoryFilter(String category) {
		Select select = new Select(categoryFilter);
		select.selectByVisibleText(category);
	}

	public String getFilteredResults() {
		return "" + filteredResults.size();
	}

	public String getUnfilteredResults() {
		return "" + unfilteredResults.size();
	}

	// public Boolean resultsCount(){

	// }

	public void searchForAdventure(String adventure) {
		searchAdventure.sendKeys(adventure);
	}

	public void selectAdventure(String adventure) throws InterruptedException {
		Thread.sleep(2000);
		custom_click(driver, filteredResults.get(0));
	}

}