package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-system-configuration.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String strongSpamTerms, final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "System Details");

		super.checkColumnHasValue(recordIndex, 0, systemCurrency);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("deadline", strongSpamTerms);
		super.fillInputBoxIn("salary", strongSpamThreshold);
		super.fillInputBoxIn("score", weakSpamTerms);
		super.fillInputBoxIn("moreInfo", weakSpamThreshold);
		super.clickOnSubmit("Update");

		super.checkColumnHasValue(recordIndex, 0, systemCurrency);
		super.checkColumnHasValue(recordIndex, 1, acceptedCurrencies);
		super.checkColumnHasValue(recordIndex, 2, strongSpamTerms);
		super.checkColumnHasValue(recordIndex, 3, strongSpamThreshold);
		super.checkColumnHasValue(recordIndex, 4, weakSpamTerms);
		super.checkColumnHasValue(recordIndex, 5, weakSpamThreshold);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);

		super.signOut();
	}
	
}
