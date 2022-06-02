package acme.testing.inventor.chimpum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorChimpumCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int itemIndex, final int recordIndex,
		final String title, final String description, final String startDate, final String
		endDate, final String budget, final String link) {

		super.signIn("inventor1", "inventor1");

		super.navigateHome();

		//tools
		super.clickOnMenu("Inventor", "My Tools");
		this.clickOnListingRecord(itemIndex);
		super.clickOnButton("Create chimpum");

		super.fillInputBoxIn("title", title);

		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);


		super.clickOnSubmit("Create");

		super.navigateHome();
		super.clickOnMenu("Inventor", "My chimpum");
		//chimpums
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, budget);
		this.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();

	} 
}
