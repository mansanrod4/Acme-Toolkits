package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int patronageIndex, final String code, final String startDate, final String endDate, final String legalStuff, final String budget, final String info, final String inventorFullName,
		final String inventorEmail, final String inventorCompany, final String inventorStatement, final String inventorInfo) {
	
	}
	
}
