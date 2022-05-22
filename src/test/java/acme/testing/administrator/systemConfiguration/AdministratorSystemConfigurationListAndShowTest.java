package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationListAndShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/show-system-configuration.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, 
		final String strongSpamTerms,final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {
		
		super.signIn("administrator", "administrator");
		
		super.navigateHome();
		super.clickOnMenu("System Configuration", "System Details");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		
		super.signOut();
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();
		super.signOut();
	}
	
}
