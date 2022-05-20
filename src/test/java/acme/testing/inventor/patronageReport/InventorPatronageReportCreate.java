
package acme.testing.inventor.patronageReport;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorPatronageReportCreate extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void checkListAllPatronageReportShow(final int reportIndex, final int recordIndex, final String reference,  final String memorandum, final String info) {

		
		super.signIn("inventor1", "inventor1");

		super.navigateHome();

		//patronages
		super.clickOnMenu("Inventor", "My Patronages");
		this.clickOnListingRecord(reportIndex);
		super.clickOnButton("Create Report");

		//reports
//		super.sortListing(0, "asc");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");

		super.clickOnSubmit("Create");

//		super.clickOnMenu("Inventor", "My Patronages");
//		this.clickOnListingRecord(reportIndex);
		super.clickOnButton("Reports");

		//reports
//		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, memorandum);
		super.checkColumnHasValue(recordIndex, 2, info);
		this.clickOnListingRecord(recordIndex);

		super.checkFormExists();
//		super.checkInputBoxHasValue("moment", moment);
//		super.checkInputBoxHasValue("memorandum", memorandum);
//		super.checkInputBoxHasValue("info", info);
//		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		
		super.signOut();

	}
}
