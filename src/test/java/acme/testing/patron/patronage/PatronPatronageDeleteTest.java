
package acme.testing.patron.patronage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/delete-patronage-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronageTest(final int recordIndex) {

		super.signIn("patron4", "patron4");
		super.navigateHome();

		super.clickOnMenu("Patron", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkNotListingEmpty();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/patron/patronage/delete");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/patron/patronage/delete");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/patronage/delete");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron3", "patron3");
		super.navigateHome();
		super.clickOnMenu("Patron", "My Patronages");
		super.clickOnListingRecord(1);
		final String url = super.getCurrentUrl();
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.navigate(url);
		super.checkPanicExists();
		super.signOut();

	}

}
