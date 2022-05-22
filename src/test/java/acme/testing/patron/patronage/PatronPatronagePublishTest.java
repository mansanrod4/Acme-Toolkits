
package acme.testing.patron.patronage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronagePublishTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-patronage-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronageTest(final int recordIndex) {

		super.navigateHome();

        super.signIn("patron4", "patron4");
        super.clickOnMenu("Patron", "My Patronages");

        super.checkListingExists();
        super.sortListing(0, "asc");

        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.clickOnSubmit("Publish");
        super.checkNotErrorsExist();
        
        super.clickOnListingRecord(recordIndex);
        super.checkNotSubmitExists("Publish");
    
        super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/patron/patronage/publish");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/patron/patronage/publish");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/patronage/publish");
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
