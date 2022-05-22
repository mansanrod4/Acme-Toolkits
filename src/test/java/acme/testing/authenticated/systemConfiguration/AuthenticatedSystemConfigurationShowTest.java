
package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemConfiguration/show-system-configuration.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSystemConfigurationTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies) {

		super.signIn("patron1", "patron1");

		super.navigateHome();
		super.clickOnMenu("System Configuration", "Currencies information");

		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/authenticated/systemConfiguration/");
		super.checkPanicExists();
	}
	

}
