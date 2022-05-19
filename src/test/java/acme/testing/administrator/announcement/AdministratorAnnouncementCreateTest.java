/*
 * EmployerDutyCreateTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness {

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-announcement-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int jobRecordIndex, final int dutyRecordIndex, final String title, final String description, final String workLoad, final String moreInfo) {

		super.navigateHome();
		super.clickOnMenu("administrator", "Chirps");
		
		super.checkListingExists();
		super.clickOnButton("Create Chirp");
	//	super.fillInputBoxIn("moment", moment);
		super.fillInputBoxIn("title", title);
//		super.fillInputBoxIn("author", author);
//		super.fillInputBoxIn("body", body);
//		super.fillInputBoxIn("email", email);
		
		super.fillInputBoxIn("confirmation", "true");
	
		super.clickOnSubmit("Create");
	
	
		super.navigateHome();
		super.clickOnMenu("Posts", "Chirps");
		super.sortListing(2, "asc");
//		
//		super.checkColumnHasValue(recordIndex, 0, moment);
//		super.checkColumnHasValue(recordIndex, 1, title);
//		super.checkColumnHasValue(recordIndex, 2, author);
//		super.checkColumnHasValue(recordIndex, 3, body);
//		super.checkColumnHasValue(recordIndex, 4, email);
	
			
	}

	
}
