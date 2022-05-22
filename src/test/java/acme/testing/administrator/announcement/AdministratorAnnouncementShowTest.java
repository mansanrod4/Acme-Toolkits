package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSystemConfigurationTest(final int recordIndex, final String title, final String moment, 
		final String body, final String critical, final String link) {
		super.signIn("administrator", "administrator");
		super.navigateHome();
		super.clickOnMenu("Administrator", "Announcements");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, moment);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("link", link);
		super.signOut();
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/administrator/systemConfiguration/");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/systemConfiguration/");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/systemConfiguration/");
		super.checkPanicExists();
		super.signOut();
	}
	
}
