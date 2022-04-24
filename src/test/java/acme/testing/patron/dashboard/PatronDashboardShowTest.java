
package acme.testing.patron.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronDashboardShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patron-dashboard/patron-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSystemConfigurationTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies) {
		
		super.signIn("patron1", "patron1");
		
		super.navigateHome();
		super.clickOnMenu("Patron", "Dashboard");

		super.checkFormExists();
		
		super.signOut();		
	}

}
