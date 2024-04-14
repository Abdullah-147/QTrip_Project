package qtriptest.tests;

import qtriptest.DP;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testCase_02 extends BaseTest {

	@Test(description="TestCase2",priority=2,groups = "Search and Filter flow",enabled = true,dataProvider = "dataProviderMethod",dataProviderClass = DP.class)
	public void UI_TestCase02(String cityName,String category,String Duration, String filteredCount,String unfilteredCount) throws InterruptedException {
		// Goto Homepage
		homePage.navigateToHomePage();

		// Search for an invalid city
//		homePage.searchForCity("Pune");
//		Boolean isNoCityDislpayed = homePage.verifySearchResults("Pune",false);
//		Assert.assertTrue(isNoCityDislpayed);

		// Search for a valid city
		homePage.searchForCity(cityName);
		Boolean isCityDisplayed = homePage.verifySearchResults(cityName,true);
		Assert.assertTrue(isCityDisplayed);

		// Adventures Page
		Assert.assertTrue(adventurePage.ifLandedOnAdventuresPage());
		adventurePage.clearAllFilters();

		// Apply Adventure filters
		adventurePage.applyDurationFilter(Duration);
		adventurePage.applyCategoryFilter(category);
		//Thread.sleep(4000);
		// Get Filtered Results
		Assert.assertEquals(adventurePage.getFilteredResults(), filteredCount);

		// Get unfiltered results
		adventurePage.clearAllFilters();
		
		//Thread.sleep(4000);
		Assert.assertEquals(adventurePage.getUnfilteredResults(), unfilteredCount);

	}

}
