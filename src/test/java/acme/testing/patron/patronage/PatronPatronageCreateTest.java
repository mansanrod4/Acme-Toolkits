package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.springframework.core.annotation.Order;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class PatronPatronageCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int patronageIndex, final String code,
		final String inventorFullName,
		final String startDate,	final String endDate, final String legalStuff, final String budget, final String info) {
	
		super.signIn("patron3", "patron3");
		
		super.navigateHome();
		
		super.clickOnMenu("Patron", "My Patronages");
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		final BrowserDriver driver = super.getDriver();
        driver.locateOne(By.id("inventor3")).click();
        driver.locateOne(By.id("inventor3")).click();

        
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("info", info);
		
		super.clickOnSubmit("Create");
		this.clickOnListingRecord(patronageIndex);
		
		super.checkInputBoxHasValue("code", code);
		//no terminado
		
		
	}
	
}
